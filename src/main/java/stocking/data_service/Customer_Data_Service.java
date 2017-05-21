package stocking.data_service;

import stocking.po.CustomerPO;

/**
 * Created by dell on 2017/5/21.
 */
public interface Customer_Data_Service {
    CustomerPO login(CustomerPO customerPO);//return null if it's invalid

    CustomerPO signUp(CustomerPO customerPO);

    CustomerPO modify(CustomerPO customerPO);
}
