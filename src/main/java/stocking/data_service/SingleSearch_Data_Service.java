package stocking.data_service;

import stocking.po.StockPO;

import java.text.ParseException;

/**
 * Created by dell on 2017/5/21.
 */
public interface SingleSearch_Data_Service {
    StockPO getStockList(String[] quest) throws ParseException;
}
