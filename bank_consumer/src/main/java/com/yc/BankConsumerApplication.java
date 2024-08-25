package com.yc;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@EnableCaching//开启缓存
@EnableAsync
public class BankConsumerApplication {
    public static void main(String[] args) {
        System.out.println("BankConsumerApplication");
        SpringApplication.run(BankConsumerApplication.class, args);
    }
}