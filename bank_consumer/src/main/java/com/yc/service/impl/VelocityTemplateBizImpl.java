package com.yc.service.impl;

import com.yc.bean.Accounts;
import com.yc.service.VelocityTemplateBiz;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;


@Service
public class VelocityTemplateBizImpl implements VelocityTemplateBiz {

    @Autowired
    private VelocityContext context;
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


    public String genEmailContent(String opType, Accounts account, double money, int toaccountid) {
        String info;
        if (opType.equals("deposit")){
            info = deposit(account, money);
        }else if (opType.equals("withdraw")){
            info = withdraw(account, money);
        }else if (opType.equals("transfer")){
            info = transfer(account, money, toaccountid);
        } else {
            info = "";
        }
        return info;
    }

    private String deposit(Accounts account, double money) {
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


    private String withdraw(Accounts account, double money) {
        Date d = new Date();
        //模板上下文，用于存占位符的值
        context.put("accountid", account.getAccountId());
        context.put("email", account.getEmail());
        context.put("subject","存款操作通知");
        context.put("optime",fullDf.format(d));
        context.put("money",money);
        context.put("balance",account.getBalance());
        context.put("currentDate",partDf.format(d));


        try(StringWriter writer = new StringWriter()){
            //template.merge(context,writer);//合并内容，替换占位符
            withdrawTemplate.merge(context,writer);//合并内容，替换占位符
            return writer.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    private String transfer(Accounts account, double money, int toAccountId) {
        Date d = new Date();

        //模板上下文，用于存占位符的值
        context.put("accountid", account.getAccountId());
        context.put("email", account.getEmail());
        context.put("subject","存款操作通知");
        context.put("optime",fullDf.format(d));
        context.put("money",money);
        context.put("balance",account.getBalance());
        context.put("currentDate",partDf.format(d));
        context.put("toaccountid",toAccountId);

        //合并模板和容器

        try(StringWriter writer = new StringWriter()){
            //template.merge(context,writer);//合并内容，替换占位符
            transferTemplate.merge(context,writer);//合并内容，替换占位符
            return writer.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

}