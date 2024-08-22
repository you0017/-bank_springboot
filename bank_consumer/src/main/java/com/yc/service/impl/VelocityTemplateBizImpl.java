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
        /*Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        String dateStr = df.format(date);
        String s = "尊敬的%s先生:\n\t您的账户%s于%s存入了%s元,当前余额为%s元,\n\t\t\t\t中国银行";
        s = String.format(s, account.getAccountid(), account.getAccountid(), dateStr, money, account.getBalance());
        return s;*/


        Date d = new Date();
        //DateFormat fullDf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        //DateFormat partDf = new SimpleDateFormat("yyyy年MM月dd日 北京时间hh时");

        //spring托管，但是要借助springmvc的视图解析器，先不管
        //VelocityEngine velocityEngine = new VelocityEngine();//TODO 没有让spring托管这个VelocityEngine
        //velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");//设置从什么位置加载模板vm
        //velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        //velocityEngine.init();

        //托管
        //VelocityContext context = new VelocityContext();//模板上下文，用于存占位符的值
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
        /*Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        String dateStr = df.format(date);
        String s = "尊敬的%s先生:\n\t您的账户%s于%s取出了%s元,当前余额为%s元,\n\t\t\t\t中国银行";
        s = String.format(s, account.getAccountid(), account.getAccountid(), dateStr, money, account.getBalance());
        return s;*/

        Date d = new Date();
        //DateFormat fullDf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        //DateFormat partDf = new SimpleDateFormat("yyyy年MM月dd日 北京时间hh时");

        //spring托管，但是要借助springmvc的视图解析器，先不管
        //VelocityEngine velocityEngine = new VelocityEngine();//TODO 没有让spring托管这个VelocityEngine
        //velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");//设置从什么位置加载模板vm
        //velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        //velocityEngine.init();

        //托管
        //VelocityContext context = new VelocityContext();//模板上下文，用于存占位符的值
        context.put("accountid", account.getAccountId());
        context.put("email", account.getEmail());
        context.put("subject","存款操作通知");
        context.put("optime",fullDf.format(d));
        context.put("money",money);
        context.put("balance",account.getBalance());
        context.put("currentDate",partDf.format(d));

        //合并模板和容器
        //Template template = velocityEngine.getTemplate("vms/withdraw.vm","utf-8");

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
        /*Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        String dateStr = df.format(date);
        String s = "尊敬的%s先生:\n\t您的账户%s于%s向账户%s转入了%s元,当前余额为%s元,\n\t\t\t\t中国银行";
        s = String.format(s, account.getAccountid(), account.getAccountid(), dateStr, toAccountId, money, account.getBalance());
        return s;*/

        Date d = new Date();
        //DateFormat fullDf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        //DateFormat partDf = new SimpleDateFormat("yyyy年MM月dd日 北京时间hh时");

        //spring托管，但是要借助springmvc的视图解析器，先不管
        //VelocityEngine velocityEngine = new VelocityEngine();//TODO 没有让spring托管这个VelocityEngine
        //velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");//设置从什么位置加载模板vm
        //velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        //velocityEngine.init();

        //托管
        //VelocityContext context = new VelocityContext();//模板上下文，用于存占位符的值
        context.put("accountid", account.getAccountId());
        context.put("email", account.getEmail());
        context.put("subject","存款操作通知");
        context.put("optime",fullDf.format(d));
        context.put("money",money);
        context.put("balance",account.getBalance());
        context.put("currentDate",partDf.format(d));
        context.put("toaccountid",toAccountId);

        //合并模板和容器
        //Template template = velocityEngine.getTemplate("vms/transfer.vm","utf-8");

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