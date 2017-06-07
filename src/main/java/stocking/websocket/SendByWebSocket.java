package stocking.websocket;

import net.sf.json.JSONArray;
import stocking.data_service.SendByWebSock_Data_Service;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by dell on 2017/6/7.
 * 用于实时数据发送，每爬好一次数据就进行一次发送
 */
public class SendByWebSocket implements SendByWebSock_Data_Service {
    private CopyOnWriteArraySet<Market_WebSocket> market_webSockets;

    public SendByWebSocket() {
        this.market_webSockets = Market_WebSocket.webSockets;
    }

    private String toJsonString(Object po) {
        JSONArray jsonArray = JSONArray.fromObject(po);
        return jsonArray.toString();
    }

    @Override
    public void sendMarketMessage(Object po) throws IOException {
        String s = toJsonString(po);
        for (Market_WebSocket marketSocket : market_webSockets) {
            marketSocket.sendMessage(s);
        }
    }

    @Override
    public void sendStockMessage(String code, Object po) {

    }
}