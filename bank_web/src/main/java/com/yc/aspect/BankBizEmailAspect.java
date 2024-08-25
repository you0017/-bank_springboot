package com.yc.aspect;

import com.yc.bean.Accounts;
import com.yc.bean.MessageBean;
import com.yc.service.BankBiz;
import com.yc.util.JmsMessageProducer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class BankBizEmailAspect {

    @Pointcut("execution(* com.yc.service.impl.BankBizImpl.deposit(..))")
    public void deposit() {
    }

    @Pointcut("execution(* com.yc.service.impl.BankBizImpl.withdraw(..))")
    public void withdraw() {
    }

    @Pointcut("execution(* com.yc.service.impl.BankBizImpl.transfer(..))")
    public void transfer() {
    }

    @Autowired
    private BankBiz bankBiz;

    @Autowired
    private JmsMessageProducer jmsMessageProducer;



    @AfterReturning(value = "deposit() || withdraw() || transfer()")
    public void sendEmail(JoinPoint joinPoint) {

        int accountid = (int) joinPoint.getArgs()[0];
        double money = (double) joinPoint.getArgs()[1];
        int toAccountId;
        if (joinPoint.getSignature().getName().equals("transfer")){
            toAccountId = (int) joinPoint.getArgs()[2];
        } else {
            toAccountId = 0;
        }
        Accounts account = bankBiz.findAccount(accountid);
        if (account == null){
            throw new RuntimeException("账户不存在");
        }
        String email = account.getEmail();
        String info;
        String methodName = joinPoint.getSignature().getName();

        jmsMessageProducer.sendMessage(new MessageBean(account, money, toAccountId,email, methodName));
    }
}
