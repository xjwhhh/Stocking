package stocking.websocket;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;

/**
 * Created by dell on 2017/6/7.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        WebSocket websocket = null;
        try {
            websocket = new WebSocketFactory().createSocket("ws://localhost:8080/marketsocket").
                    addListener(new WebSocketAdapter() {
                        @Override
                        public void onTextMessage(WebSocket ws, String message) {
                            System.out.println("Received msg: " + message);
                        }
                    }).connect();

        } catch (Exception e) {
            websocket.disconnect();
            e.printStackTrace();
        }
        //这里写爬虫
        if (websocket != null)
            while(true) {
                websocket.sendText("hello!");
                Thread.sleep(10000);
            }
    }
}
