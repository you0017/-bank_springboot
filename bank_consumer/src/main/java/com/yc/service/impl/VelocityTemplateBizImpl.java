package com.yc.service.impl;

import com.yc.bean.Accounts;
import com.yc.service.VelocityTemplateBiz;
import com.yc.strategy.MessageStrategy;
import com.yc.strategy.impl.StrategyContext;
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
    private StrategyContext strategyContext;


    public String genEmailContent(String opType, Accounts account, double money, int toAccountId) {

        String s = strategyContext.executeStrategy(opType, account, money, toAccountId);
        return s;
    }


}