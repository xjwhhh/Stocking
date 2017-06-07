package stocking.websocket;

import org.junit.Before;
import org.junit.Test;
import stocking.data_service.SendByWebSock_Data_Service;

import static org.junit.Assert.*;

/**
 * Created by dell on 2017/6/7.
 */
public class SendByWebSocketTest {
    private SendByWebSock_Data_Service sendByWebSocket;

    @Before
    public void setUp() throws Exception {
        sendByWebSocket = new SendByWebSocket();
    }

    @Test
    public void sendMarketMessage() throws Exception {
        String s = "hello";
        while (true) {
            sendByWebSocket.sendMarketMessage(s);
        }
    }

    @Test
    public void sendStockMessage() throws Exception {

    }

}