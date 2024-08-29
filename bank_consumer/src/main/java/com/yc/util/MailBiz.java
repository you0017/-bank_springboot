package com.yc.util;

import com.yc.bean.Message;
import com.yc.bean.MessageBean;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailBiz {
    @Value("${spring.mail.properties.mail.smtp.from}")
    private String from;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private WebSocketServer webSocketServer;

    @Value("${spring.cache.cacheName}")
    private String bankName;
    @Async
    public void sendMail(String to, String subject, String content, MessageBean mb,String time) {
        log.info("发送邮件：" + to + "，内容：" + mb.getOpType());
        //SimpleMailMessage mail = new SimpleMailMessage();//不包括附件
        MimeMessage mm = javaMailSender.createMimeMessage();//可以包括附件


        try {
            MimeMessageHelper message = new MimeMessageHelper(mm,true,"utf-8");//true代表可以有附件
            message.setFrom(from);//谁发
            message.setTo(to);//发给谁
            message.setSubject(subject);//主题
            message.setText(content,true);//内容,true代表传的是html
            javaMailSender.send(mm);//发送
        }catch(Exception e){
            e.printStackTrace();
        }


        Message build = Message.builder().context(content).subject(subject).messageBean(mb).time(time).build();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(bankName+time,build);

        webSocketServer.send("1");
    }
}
