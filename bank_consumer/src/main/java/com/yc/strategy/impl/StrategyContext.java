package com.yc.strategy.impl;

import com.yc.bean.Accounts;
import com.yc.strategy.MessageStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class StrategyContext {

    private final Map<String, MessageStrategy> messageStrategies;

    public String executeStrategy(String opType, Accounts account, double money, int toAccountId) {
        String info;

        MessageStrategy messageStrategy = messageStrategies.get(opType);
        if (messageStrategy != null) {
            info = messageStrategy.transformation(account, money, toAccountId);
        } else {
            throw new RuntimeException("未知操作");
        }

        return info;
    }
}
