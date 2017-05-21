package stocking.data_service;

import stocking.po.CustomerPO;

/**
 * Created by dell on 2017/5/21.
 */
public interface Customer_Data_Service {
    CustomerPO login(String name,String password);//return null if it's invalid
}
