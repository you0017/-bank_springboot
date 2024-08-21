package com.yc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

//email服务
@Configuration
public class EmailConfig {
    /**
     * 托管javaMailSender类，用于邮件发送
     */
    @Bean
    public JavaMailSender mailSender(@Value("${smtp.host}")String host,
                                     @Value("${smtp.port}")int port,
                                     @Value("${smtp.auth}")String auth,
                                     @Value("${smtp.from}")String from,
                                     @Value("${smtp.username}")String username,
                                     @Value("${smtp.password}")String password
                                     ){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties p = mailSender.getJavaMailProperties();
        p.setProperty("mail.transport.protocol", "smtp");
        p.setProperty("mail.smtp.auth", auth);
        if (port == 587){
            p.put("mail.smtp.starttls.enable", "true");
        }
        if (port == 465){
            //加密
            p.put("mail.smtp.socketFactory.port", "465");
            p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            p.put("mail.smtp.ssl.protocols", "TLSv1.2");
        }

        return mailSender;
    }
}
