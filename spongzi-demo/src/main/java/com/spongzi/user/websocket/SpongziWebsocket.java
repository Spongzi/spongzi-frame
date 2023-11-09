package com.spongzi.user.websocket;

import com.spongzi.webscoket.config.WebSocketServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.context.Theme;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * spongzi websocket
 *
 * @author spong
 * @date 2023/11/09
 */
@Slf4j
@Component
@ServerEndpoint(value = "/spongzi/socket", configurator = WebSocketServerConfig.class)
public class SpongziWebsocket {

    /**
     * 记录当前在线连接数
     */
    private static final AtomicInteger onlineCline = new AtomicInteger(0);

    /**
     * 存放所有的在线客户端
     */
    private static final Map<String, SpongziWebsocket> clients = new ConcurrentHashMap<>();

    /**
     * 某个客户端连接的session会话
     */
    private Session session;

    /**
     * 表示当前会话的key
     */
    private String erp = "";

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        Map<String, Object> userProperties = config.getUserProperties();
        String erp = (String) userProperties.get("erp");
        this.erp = erp;
        this.session = session;
        if (clients.containsKey(erp)) {
            clients.get(this.erp).session.close();
            clients.remove(this.erp);
            onlineCline.decrementAndGet();
        }
        clients.put(this.erp, this);
        onlineCline.incrementAndGet();
        log.info("有新链接加入：{}", erp);
        log.info("当前在线人数{}", onlineCline.get());
        sendMessage("连接成功", session);
    }

    @OnClose
    public void onClose() {
        if (clients.containsKey(erp)) {
            try {
                clients.get(erp).session.close();
                log.info("关闭连接成功");
            } catch (IOException e) {
                log.error("关闭连接失败", e);
                throw new RuntimeException(e);
            }
            clients.remove(erp);
            onlineCline.decrementAndGet();
        }
        log.info("当前在线人数：{}", onlineCline.get());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("服务端收到客户端{}消息{}", this.erp, message);
        // 心跳机制
        if (message.equals("ping")) {
            this.sendMessage("pong", session);
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) throws IOException {
        log.error("websocket：{}，发送错误，错误原因：{}", erp, throwable.getMessage(), throwable);
        session.close();
    }

    public void sendMessage(String message, Session session) throws IOException {
        log.info("服务端给客户端{}发送消息：{}", this.erp, message);
        // 可能会失败
        session.getBasicRemote().sendText(message);
    }

    public void sendMessage(String message) throws IOException {
        for (Map.Entry<String, SpongziWebsocket> sessionEntry : clients.entrySet()) {
            String erp = sessionEntry.getKey();
            SpongziWebsocket websocket = sessionEntry.getValue();
            Session session = websocket.session;
            session.getBasicRemote().sendText(message);
        }
    }
}
