package com.yc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.yc.mapper")
@EnableCaching
public class BankWebApplication {
    public static void main(String[] args) {
        System.out.println("启动成功");
        SpringApplication.run(BankWebApplication.class, args);
    }
}