package com.yc.service.impl;

import com.yc.bean.Message;
import com.yc.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取所有以 message 开头的键值对
     * @return 键值对集合
     */
    public List<Message> getMessagesWithPrefix() {
        // 获取所有以 "message" 开头的键
        Set<String> keys = redisTemplate.keys("message:*");

        if (keys != null && !keys.isEmpty()) {
            return keys.stream()
                    .map(key -> redisTemplate.opsForValue().get(key))
                    .filter(value -> value instanceof Message) // 确保值是 Message 实例
                    .map(value -> (Message) value) // 转换为 Message 类型
                    .sorted((m1, m2) -> {
                        // 按时间属性排序，假设时间字符串可以按字典顺序排序
                        return m2.getTime().compareTo(m1.getTime());
                    })
                    .collect(Collectors.toList());
        }
        return List.of(); // 返回一个空的不可变List
    }
}
