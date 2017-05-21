package stocking.data_impl;

import stocking.data_service.Customer_Data_Service;
import stocking.po.CustomerPO;

/**
 * Created by dell on 2017/5/21.
 */
public class Customer_Data_Impl implements Customer_Data_Service{
    public CustomerPO login(String name, String password) {
        /*
        *an example
         */
        return new CustomerPO("wuyuhan","12345");
    }
}
