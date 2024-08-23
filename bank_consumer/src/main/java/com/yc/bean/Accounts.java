package com.yc.bean;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


//@ManagedResource(objectName = "com.yc:name=Accounts")
//@Component
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Accounts implements Serializable {
    private Integer accountId;
    private Double balance;
    private String email;
}
