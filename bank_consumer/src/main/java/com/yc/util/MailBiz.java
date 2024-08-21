package com.yc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Component
public class MailBiz {
    @Value("${smtp.from}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String to, String subject, String content) {
        //SimpleMailMessage mail = new SimpleMailMessage();//不包括附件
        MimeMessage mm = mailSender.createMimeMessage();//可以包括附件


        try {
            MimeMessageHelper message = new MimeMessageHelper(mm,true,"utf-8");//true代表可以有附件
            message.setFrom(from);//谁发
            message.setTo(to);//发给谁
            message.setSubject(subject);//主题
            message.setText(content,true);//内容,true代表传的是html
            mailSender.send(mm);//发送
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
