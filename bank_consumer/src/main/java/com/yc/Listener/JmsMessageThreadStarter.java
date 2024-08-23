package com.yc.Listener;

import com.google.gson.Gson;
import com.yc.bean.MessageBean;
import com.yc.service.VelocityTemplateBiz;
import com.yc.util.MailBiz;
import com.yc.util.WebSocketServer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 程序启动时启动这个线程来接收消息
 */
@Component
public class JmsMessageThreadStarter implements CommandLineRunner {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${spring.activemq.queue}")
    private String queue;
    @Autowired
    private VelocityTemplateBiz velocityTemplateBiz;
    @Autowired
    private MailBiz mailBiz;
    @Autowired
    private WebSocketServer webSocketServer;


    @PostConstruct
    public void init() {
        // 启动一个线程来接收消息
        new Thread(() -> {
            while (true) {
                // 轮询或其他逻辑来接收消息
                // 你可以使用 JmsTemplate 来接收消息，通常是生产者代码
                try {
                    String message = (String) jmsTemplate.receiveAndConvert(queue);

                    System.out.println("接收到消息：" + message);
                    Gson gson = new Gson();
                    MessageBean mb = gson.fromJson(message, MessageBean.class);
                    //产生要发送的邮件内容
                    String context = velocityTemplateBiz.genEmailContent(mb.getOpType(),mb.getAccount(),mb.getMoney(),mb.getToAccountId());
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    mailBiz.sendMail(mb.getAccount().getEmail(),"账户变动通知",context,mb, simpleDateFormat.format(date));

                    webSocketServer.send("1");


                    Thread.sleep(1000); // 延迟一秒钟后继续轮询
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }

    @Override
    public void run(String... args) throws Exception {
        // 这里可以放其他启动逻辑
    }
}

