package com.yc.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Configuration
@Log4j2
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("insertFill");
        log.info("insertFill");
        strictFillStrategy(metaObject,"opTime", ()->{return LocalDateTime.now().toString();});
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("updateFill");
        log.info("updateFill");
        strictFillStrategy(metaObject,"optime", ()->{return LocalDateTime.now().toString();});
    }
}
