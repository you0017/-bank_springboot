package com.yc.task;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
@Slf4j
public class RedisTask {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CacheManager cacheManager;
    @Scheduled(cron = "1 * * * * ?")
    public void task() {
        //只能删springCache操作的，如果是redisTemplate添加修改的就无法操作
        cacheManager.getCacheNames().forEach(name -> {
            cacheManager.getCache(name).clear();
            log.info("清除缓存：{}",name);
        });

        ValueOperations valueOperations = redisTemplate.opsForValue();
        Set keys = redisTemplate.keys("message*");
        Set keys1 = redisTemplate.keys("bank*");
        if (keys!=null&&!keys.isEmpty()){
            redisTemplate.delete(keys);
        }
        if (keys1!=null&&!keys1.isEmpty()){
            redisTemplate.delete(keys1);
        }
        log.info("redis task");
    }
}
