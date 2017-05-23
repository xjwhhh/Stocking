package stocking.data_service;

import stocking.po.StockPO;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by dell on 2017/5/21.
 */
public interface SingleSearch_Data_Service {
    StockPO getStockList(String identifier, Date start, Date end) throws ParseException;//可能传code或名称
}
