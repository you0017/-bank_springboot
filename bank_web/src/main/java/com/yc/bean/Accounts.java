package com.yc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@ManagedResource(objectName = "com.yc:name=Accounts")
@Component
public class Accounts implements Serializable {
    @TableId(value = "accountid", type = IdType.AUTO)
    private Integer accountId;
    private Double balance;
    private String email;

    public Accounts() {
    }

    @ManagedAttribute
    public Integer getAccountId() {
        return accountId;
    }

    @ManagedOperation
    @ManagedOperationParameter(name = "accountid", description = "账户id")
    public void setAccountId(Integer accountid) {
        this.accountId = accountid;
    }
    @ManagedAttribute
    public Double getBalance() {
        return balance;
    }
    @ManagedOperation
    @ManagedOperationParameter(name = "balance", description = "账户金额")
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    @ManagedAttribute
    public String getEmail() {
        return email;
    }
    @ManagedOperation
    @ManagedOperationParameter(name = "email", description = "账户邮箱")
    public void setEmail(String email) {
        this.email = email;
    }
}
