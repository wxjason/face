package cn.wxj.face.admin.websocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName: WebSocket
 * @Package com.tce.yunshi.alarm.websocket.config
 * @Description:
 * @Author wuxinjian
 * @Date 2018/11/16 9:49
 * @Version V1.0
 */
@ServerEndpoint(value = "/websocket/{username}")
@Component
@Slf4j
public class WebSocket {

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
     */
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    private String username;

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        Objects.requireNonNull(username);
        this.session = session;
        this.username = username;
        //加入set中
        webSocketSet.add(this);
        log.info("【WebSocket】OnOpen：有新连接加入[{}]！当前在线人数为{}", username, getOnlineCount());
        try {
            sendMessage("加入连接成功");
        } catch (IOException e) {
            log.error("新连接加入失败", e);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从set中删除
        webSocketSet.remove(this);
        log.info("【WebSocket】OnClose：有一连接关闭[{}]！当前在线人数为{}", this.username, getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("【WebSocket】OnMessage[{}]：{}", this.username, message);
        message = this.username + "：" + message;
        //群发消息
        for (WebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (Exception e) {
                log.error("【WebSocket】发送消息异常", e);
            }
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("【WebSocket】OnError[{}]", this.username);
        log.error("【WebSocket】OnError：", error);
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendMsg(String message) {
        log.info("【WebSocket】广播：{}", message);
        for (WebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (Exception e) {
                log.error("【WebSocket】发送消息异常", e);
            }
        }
    }

    /**
     * 群发自定义消息
     */
    public static void sendMsg(String username, String message) {
        if (StringUtils.isEmpty(username)) {
            sendMsg(message);
            return;
        }
        log.info("【WebSocket】广播：{}", message);
        for (WebSocket item : webSocketSet) {
            try {
                if (username.equals(item.username)) {
                    item.sendMessage(message);
                }
            } catch (Exception e) {
                log.error("【WebSocket】发送消息异常", e);
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return webSocketSet.size();
    }
/// 暂不使用
//    @Override
//    public boolean equals(Object o) {
//        if (o == null) {
//            return false;
//        }
//        if (!(o instanceof WebSocket)) {
//            return false;
//        }
//
//        WebSocket that = (WebSocket) o;
//
//        return username.equals(that.username);
//    }
//
//    @Override
//    public int hashCode() {
//        return username.hashCode();
//    }
}
