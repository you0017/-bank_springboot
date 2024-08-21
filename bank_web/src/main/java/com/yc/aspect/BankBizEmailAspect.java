package com.yc.aspect;


import com.yc.bean.Accounts;
import com.yc.bean.MessageBean;
import com.yc.service.BankBiz;
import com.yc.util.JmsMessageProducer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
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

    //@Autowired
    //private MailBiz mailBiz;

    @Autowired
    private BankBiz bankBiz;


    /*@Autowired
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
*/
    @Autowired
    private JmsMessageProducer jmsMessageProducer;



    @After(value = "deposit() || withdraw() || transfer()")
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
        String email = account.getEmail();
        String info;
        String methodName = joinPoint.getSignature().getName();
        /*if (methodName.equals("deposit")){
            info = deposit(account, money);
        }else if (methodName.equals("withdraw")){
            info = withdraw(account, money);
        }else if (methodName.equals("transfer")){
            info = transfer(account, money, toAccountId);
        } else {
            info = "";
        }*/
        new Thread(() -> {
            //mailBiz.sendMail(email, "银行通知", info);
            jmsMessageProducer.sendMessage(new MessageBean(account, money, toAccountId,email, methodName));
        }).start();
    }

    /*private String deposit(Account account, double money) {
        *//*Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        String dateStr = df.format(date);
        String s = "尊敬的%s先生:\n\t您的账户%s于%s存入了%s元,当前余额为%s元,\n\t\t\t\t中国银行";
        s = String.format(s, account.getAccountid(), account.getAccountid(), dateStr, money, account.getBalance());
        return s;*//*


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
        context.put("accountid", account.getAccountid());
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


    private String withdraw(Account account, double money) {
        *//*Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        String dateStr = df.format(date);
        String s = "尊敬的%s先生:\n\t您的账户%s于%s取出了%s元,当前余额为%s元,\n\t\t\t\t中国银行";
        s = String.format(s, account.getAccountid(), account.getAccountid(), dateStr, money, account.getBalance());
        return s;*//*

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
        context.put("accountid", account.getAccountid());
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

    private String transfer(Account account, double money, int toAccountId) {
        *//*Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
        String dateStr = df.format(date);
        String s = "尊敬的%s先生:\n\t您的账户%s于%s向账户%s转入了%s元,当前余额为%s元,\n\t\t\t\t中国银行";
        s = String.format(s, account.getAccountid(), account.getAccountid(), dateStr, toAccountId, money, account.getBalance());
        return s;*//*

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
        context.put("accountid", account.getAccountid());
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
    }*/

}
