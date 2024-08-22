package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageBean {
    private Accounts account;
    private double money;
    private Integer toAccountId;
    private String email;
    private String opType;
}
