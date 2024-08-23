package com.yc.util;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * WebSocket服务
 */
@Component
@ServerEndpoint("/ws")
public class WebSocketServer {

    private static Session session;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket 连接已经建立。");
        WebSocketServer.session = session;
    }
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("收到客户端消息：" + message);
        //session.getBasicRemote().sendText("服务器收到消息：" + message);
    }

    @OnClose
    public void onClose() {
        System.out.println("WebSocket 连接已经关闭。");
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("WebSocket 连接出现错误：" + t.getMessage());
    }

    public void send(String message) {
        try {
            session.getBasicRemote().sendText(message);
        }catch (Exception e){
            System.out.println("WebSocket发送消息失败：" + e.getMessage());
        }
    }
}
