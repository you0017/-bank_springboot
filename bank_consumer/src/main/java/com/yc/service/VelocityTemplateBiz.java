package com.yc.service;

import com.yc.bean.Account;

import java.io.StringWriter;
import java.util.Date;

public interface VelocityTemplateBiz {
    public String genEmailContent(String opType, Account account, double money, int toaccountid);
}
