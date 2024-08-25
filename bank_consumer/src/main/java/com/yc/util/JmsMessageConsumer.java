package com.yc.util;

import com.google.gson.Gson;
import com.yc.bean.MessageBean;
import com.yc.service.VelocityTemplateBiz;
import com.yc.service.impl.VelocityTemplateBizImpl;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Log4j2
public class JmsMessageConsumer {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${spring.activemq.queue}")
    private String queue;
    @Autowired
    private VelocityTemplateBiz velocityTemplateBiz;
    @Autowired
    private MailBiz mailBiz;


    @JmsListener(destination = "bankMessages")//监听myQueue消息队列
    public void receiverMessage(String message){
        System.out.println("接收到消息：" + message);
        Gson gson = new Gson();
        MessageBean mb = gson.fromJson(message, MessageBean.class);
        //产生要发送的邮件内容
        String context = velocityTemplateBiz.genEmailContent(mb.getOpType(),mb.getAccount(),mb.getMoney(),mb.getToAccountId());
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mailBiz.sendMail(mb.getAccount().getEmail(),"账户变动通知",context,mb, simpleDateFormat.format(date));

    }
}
