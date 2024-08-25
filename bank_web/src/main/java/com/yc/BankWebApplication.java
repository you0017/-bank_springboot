package com.yc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.yc.mapper")
@EnableCaching
@EnableAsync//启动异步，线程池用
//@EnableScheduling
public class BankWebApplication {
    public static void main(String[] args) {
        //System.out.println("启动成功");
        SpringApplication.run(BankWebApplication.class, args);
    }
}