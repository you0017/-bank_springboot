package com.yc.controller;


import com.yc.bean.JsonModel;
import com.yc.bean.Message;
import com.yc.service.CacheService;
import com.yc.service.impl.CacheServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/normal.action")
public class NormalController {

    @Autowired
    private CacheService cacheService;

    @GetMapping
    public JsonModel getServerInfo(HttpServletRequest request) {
        Map<String, String> serverInfo = new HashMap<>();
        String protocol = request.isSecure() ? "wss" : "ws";
        String host = request.getServerName();
        int port = request.getServerPort();
        String contextPath = request.getContextPath();

        serverInfo.put("protocol", protocol);
        serverInfo.put("host", host);
        serverInfo.put("port", String.valueOf(port));
        serverInfo.put("contextPath", contextPath);

        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj(serverInfo);
        return jm;
    }

    @GetMapping("getAllMessage")
    public JsonModel getAllMessage() {
        List<Message> messagesWithPrefix = cacheService.getMessagesWithPrefix();
        return new JsonModel(1, messagesWithPrefix,"");
    }

}
