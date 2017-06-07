package stocking.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by dell on 2017/6/6.
 */

@ServerEndpoint("/imstock")
public class Stock_WebSocket {
    private Session session;
    private ServerEndpointConfig endpointConfig;

    @OnOpen
    public void onOpen(EndpointConfig config, Session session) {
        this.endpointConfig = (ServerEndpointConfig) config;
        this.session = session;
    }

    @OnMessage
    /**
     * @param:message代表股票代码
     */
    public void onMessage(String message, Session session) {

    }

    @OnClose
    public void onClose() {

    }

    @OnError
    public void onError(Session session, Throwable error) {

    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
}
