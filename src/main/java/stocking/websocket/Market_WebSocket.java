package stocking.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by dell on 2017/6/6.
 */
@ServerEndpoint("/marketsocket")
public class Market_WebSocket {
    private static int onlineCount = 0;

    static CopyOnWriteArraySet<Market_WebSocket> webSockets = new CopyOnWriteArraySet<Market_WebSocket>();

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);     //加入set中
        addOnlineCount();           //在线数加1
//        try {
//            sendMessage("hello");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("有新连接加入！当前在线人数为");
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为");
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    void sendMessage(String s) throws IOException {
        this.session.getBasicRemote().sendText(s);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        onlineCount--;
    }
}
