package stocking.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by dell on 2017/6/6.
 */

@ServerEndpoint("/stocksocket")
public class Stock_WebSocket {
    private static Map<String, Integer> onlineCount = new HashMap<String, Integer>();

    static Map<String, CopyOnWriteArraySet<Stock_WebSocket>> webSocketSet =
            new HashMap<String, CopyOnWriteArraySet<Stock_WebSocket>>();

    private Session session;

    //code==0，代表关闭会话
    @OnMessage
    public void onMessage(String code, Session session) {
        if (code.equals("0")) {
            subOnlineCount(code);
            webSocketSet.get(code).remove(this);
            return;
        }
        this.session = session;
        addOnlineCount(code);
        webSocketSet.get(code).add(this);
    }

    void sendMessage(String s) throws IOException {
        this.session.getBasicRemote().sendText(s);
    }

    public static synchronized int getOnlineCount(String code) {
        return onlineCount.get(code);
    }

    private static synchronized void addOnlineCount(String code) {
        if (!onlineCount.containsKey(code)) {
            onlineCount.put(code, 1);
            CopyOnWriteArraySet<Stock_WebSocket> webSockets = new CopyOnWriteArraySet<Stock_WebSocket>();
            webSocketSet.put(code, webSockets);
        } else {
            int temp = onlineCount.get(code);
            temp++;
            onlineCount.put(code, temp);
        }
    }

    private static synchronized void subOnlineCount(String code) {
        int temp = onlineCount.get(code);
        temp--;
        onlineCount.put(code, temp);
    }
}
