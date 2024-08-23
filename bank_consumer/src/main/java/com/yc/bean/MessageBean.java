package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MessageBean implements Serializable {
    private Accounts account;
    private double money;
    private Integer toAccountId;
    private String email;
    private String opType;
}
