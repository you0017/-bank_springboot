package com.yc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


//@ManagedResource(objectName = "com.yc:name=Accounts")
//@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts implements Serializable {
    @TableId(value = "accountid", type = IdType.AUTO)
    private Integer accountId;
    private Double balance;
    private String email;
}
