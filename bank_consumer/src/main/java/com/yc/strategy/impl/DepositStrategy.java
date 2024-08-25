package com.yc.strategy.impl;

import com.yc.bean.Accounts;
import com.yc.strategy.MessageStrategy;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;
@Component
public class DepositStrategy implements MessageStrategy {
    @Autowired
    protected VelocityContext context;
    @Autowired
    @Qualifier("withdrawTemplate")
    private Template withdrawTemplate;
    @Autowired
    @Qualifier("depositTemplate")
    private Template depositTemplate;
    @Autowired
    @Qualifier("transferTemplate")
    private Template transferTemplate;
    @Autowired
    @Qualifier("fullDf")
    private DateFormat fullDf;
    @Autowired
    @Qualifier("partDf")
    private DateFormat partDf;
    @Override
    public String transformation(Accounts account, double money, int toAccountId) {
        Date d = new Date();
        //模板上下文，用于存占位符的值
        context.put("accountid", account.getAccountId());
        context.put("email", account.getEmail());
        context.put("subject","存款操作通知");
        context.put("optime",fullDf.format(d));
        context.put("money",money);
        context.put("balance",account.getBalance());
        context.put("currentDate",partDf.format(d));

        //合并模板和容器
        //Template template = velocityEngine.getTemplate("vms/deposit.vm","utf-8");

        try(StringWriter writer = new StringWriter()){
            //template.merge(context,writer);//合并内容，替换占位符
            depositTemplate.merge(context,writer);//合并内容，替换占位符
            return writer.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
