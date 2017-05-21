package stocking.data_service;

import stocking.po.CustomerPO;

/**
 * Created by dell on 2017/5/21.
 */
public interface Customer_Data_Service {
    CustomerPO execute(String op, CustomerPO customerPO);//一共有login，signup，modify三种操作，由impl决定
}
