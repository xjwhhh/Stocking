package stocking.data_service;

import stocking.po.StockInfoPO;

/**
 * Created by dell on 2017/6/5.
 */
public interface CodeName_Data_Service {
    StockInfoPO get();

    StockInfoPO getPlate(String name);
}
