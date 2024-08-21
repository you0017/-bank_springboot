package com.yc.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.bean.Accounts;
import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.mapper.AccountMapper;
import com.yc.mapper.OpRecordMapper;
import com.yc.service.BankBiz;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional(
        propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        timeout = -1,
        readOnly = false,
        rollbackFor = {RuntimeException.class}
)
@ManagedResource(objectName = "com.yc:name=BankBiz")
public class BankBizImpl extends ServiceImpl<AccountMapper, Accounts> implements BankBiz {

    @Autowired
    private OpRecordMapper opRecordMapper;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Accounts openAccount(Accounts account) {
        //操作：开户
        accountMapper.insert(account);
        //操作2：流水记录
        OpRecord record = new OpRecord();
        record.setAccountid(account.getAccountId());
        record.setOpmoney(account.getBalance());
        record.setOptype(OpType.DEPOSITE);
        opRecordMapper.insert(record);
        //操作3：构建返回值

        log.info("开户"+account.getAccountId()+",存入"+account.getBalance());
        return account;
    }

    @Override
    @CachePut(value = "bank_web",key = "#accountid")
    public Accounts deposit(int accountid, double money) {
        Accounts a = null;
        try{
            a = this.findAccount(accountid);
        }catch (Exception e){
            log.error("账户不存在:"+accountid);
            throw new RuntimeException("账户不存在:"+accountid+",无法完成存款操作");
        }
        a.setBalance(a.getBalance()+money);
        this.lambdaUpdate()
                        .eq(Accounts::getAccountId,accountid)
                        .set(Accounts::getBalance,a.getBalance())
                .update();
        //操作2：流水记录
        OpRecord record = new OpRecord();
        record.setAccountid(accountid);
        record.setOpmoney(money);
        record.setOptype(OpType.DEPOSITE);
        opRecordMapper.insert(record);
        return a;

    }

    @Override
    @CachePut(value = "bank_web",key = "#accountid")
    //@Transactional
    public Accounts withdraw(int accountid, double money) {
        Accounts a = null;
        try{
            a = this.findAccount(accountid);
        }catch (Exception e){
            log.error("账户不存在:"+accountid);
            throw new RuntimeException("账户不存在:"+accountid+",无法完成存款操作");
        }


        //操作2：流水记录
        OpRecord record = new OpRecord();
        record.setAccountid(accountid);
        record.setOpmoney(money);
        record.setOptype(OpType.WITHDRAW);
        opRecordMapper.insert(record);

        //TODO:要判断余额是否足够,利用数据库中的约束来完成金额的检查
        a.setBalance(a.getBalance()-money);
        this.lambdaUpdate()
                .eq(Accounts::getAccountId,accountid)
                .set(Accounts::getBalance,a.getBalance())
                .update();
        return a;
    }

    @Override
    @CachePut(value = "bank_web",key = "#accountId")
    public Accounts transfer(Integer accountId,Double balance, int toAccountId) {
        this.deposit(toAccountId,balance);
        Accounts a = this.withdraw(accountId,balance);
        return a;
    }

    @Override
    @Transactional(readOnly =true)
    @Cacheable(value = "bank_web",key = "#accountid")
    public Accounts findAccount(int accountid) {
        return accountMapper.selectById(accountid);
    }

    @Override
    @Cacheable(value = "bank_web",key = "#accountid")
    public Accounts email(int accountid) {
        Accounts account = this.lambdaQuery()
                .eq(Accounts::getAccountId,accountid)
                .one();
        return account;
    }
}
