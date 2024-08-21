package com.yc.util;

import com.google.gson.Gson;
import com.yc.bean.MessageBean;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class JmsMessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${spring.activemq.queue}")
    private String queueName;

    public void sendMessage(MessageBean messageBean) {
        //String message = messageBean.toString();
        //将bean转成一个json字符串，序列化后存到activeMQ
        Gson gson = new Gson();
        String message = gson.toJson(messageBean);
        log.info("发送邮件消息：" + message);
        jmsTemplate.convertAndSend(queueName,message);//发消息到队列
        log.info("发送邮件消息成功");
    }
}
