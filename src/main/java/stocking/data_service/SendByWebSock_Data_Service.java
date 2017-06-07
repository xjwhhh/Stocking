package stocking.data_service;

import java.io.IOException;

/**
 * Created by dell on 2017/6/7.
 */
public interface SendByWebSock_Data_Service {
    void sendMarketMessage(Object po) throws IOException;

    void sendStockMessage(String code, Object po);
}
