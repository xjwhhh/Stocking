package stocking.data_service;

import stocking.po.CustomerPO;

/**
 * Created by dell on 2017/5/21.
 */
public interface DataFactory_Data_Service {
    Customer_Data_Service customer();

    SingleSearch_Data_Service singleSearch();

    OverallSearch_Data_Service overall();

    CustomerPO getCustomerPO(String id,String name,String password,String newpassword);
}
