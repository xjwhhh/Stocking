package stocking.data_service;

import stocking.po.CustomerPO;
import stocking.po.StockPO;
import stocking.po.MarketPO;

/**
 * Created by dell on 2017/5/21.
 */
public interface DataFactory_Data_Service {
    Customer_Data_Service customer();

    SingleSearch_Data_Service singleSearch();

    OverallSearch_Data_Service overall();

    Strategy_Data_Service strategy();
}
