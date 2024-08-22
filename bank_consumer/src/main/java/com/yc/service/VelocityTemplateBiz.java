package com.yc.service;

import com.yc.bean.Accounts;

public interface VelocityTemplateBiz {
    public String genEmailContent(String opType, Accounts account, double money, int toaccountid);
}
