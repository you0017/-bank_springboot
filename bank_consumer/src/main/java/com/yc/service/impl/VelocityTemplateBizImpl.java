package com.yc.service.impl;

import com.yc.bean.Accounts;
import com.yc.service.VelocityTemplateBiz;
import com.yc.strategy.MessageStrategy;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;


@Service
public class VelocityTemplateBizImpl implements VelocityTemplateBiz {

    @Autowired
    private Map<String, MessageStrategy> messageStrategies;


    public String genEmailContent(String opType, Accounts account, double money, int toaccountid) {
        String info;

        MessageStrategy messageStrategy = messageStrategies.get(opType+"Strategy");
        if (messageStrategy != null) {
            info = messageStrategy.transformation(account, money, toaccountid);
        } else {
            throw new RuntimeException("未知操作");
        }

        return info;
    }


}