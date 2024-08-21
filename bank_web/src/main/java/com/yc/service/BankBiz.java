package com.yc.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yc.bean.Accounts;

public interface BankBiz extends IService<Accounts> {
    // 添加Account账号
    public Accounts openAccount(Accounts account);

    // 修改Account账号money
    public Accounts deposit(int accountid, double money);

    // 取款
    public Accounts withdraw(int accountid, double money);

    // 转账
    public Accounts transfer(Integer accountId,Double balance, int toAccountId);


    // 根据id查询Account账号信息
    public Accounts findAccount(int accountid);

    Accounts email(int accountId);
}
