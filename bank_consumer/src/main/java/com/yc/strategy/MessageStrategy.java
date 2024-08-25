package com.yc.strategy;

import com.yc.bean.Accounts;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.text.DateFormat;

public interface MessageStrategy {

    public String transformation(Accounts account, double money, int toAccountId);
}
