package stocking.data_service;

import stocking.po.MarketPO;

import java.util.Date;

/**
 * Created by dell on 2017/5/21.
 */
public interface OverallSearch_Data_Service {
    MarketPO getMarketInfo(Date date);
}
