package com.yc.service;

import com.yc.bean.Message;

import java.util.List;
import java.util.Set;

public interface CacheService {
    public List<Message> getMessagesWithPrefix();
}
