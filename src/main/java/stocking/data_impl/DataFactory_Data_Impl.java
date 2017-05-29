package stocking.data_impl;

import stocking.data_service.Customer_Data_Service;
import stocking.data_service.DataFactory_Data_Service;
import stocking.data_service.OverallSearch_Data_Service;
import stocking.data_service.SingleSearch_Data_Service;
import stocking.po.CustomerPO;
import stocking.po.StockPO;
import stocking.po.MarketPO;

/**
 * Created by dell on 2017/5/21.
 */
public class DataFactory_Data_Impl implements DataFactory_Data_Service {
    private static DataFactory_Data_Service factoryDataService;

    private DataFactory_Data_Impl() {

    }

    public static DataFactory_Data_Service getInstance() {
        if (factoryDataService == null) {
            return new DataFactory_Data_Impl();
        } else {
            return factoryDataService;
        }
    }

    public Customer_Data_Service customer() {
        return null;
    }

    public SingleSearch_Data_Service singleSearch() {
        return null;
    }

    public OverallSearch_Data_Service overall() {
        return null;
    }

}
