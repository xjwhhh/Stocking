package stocking.websocket;

import stocking.data_service.SendByWebSock_Data_Service;

import java.io.IOException;

/**
 * Created by dell on 2017/6/7.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        SendByWebSock_Data_Service sendByWebSocket = new SendByWebSocket();
        String s = "hello";
        while (true) {
            sendByWebSocket.sendMarketMessage(s);
        }
    }
}
