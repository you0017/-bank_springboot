package com.yc;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankConsumerApplication {
    public static void main(String[] args) {
        System.out.println("BankConsumerApplication");
        SpringApplication.run(BankConsumerApplication.class, args);
    }
}