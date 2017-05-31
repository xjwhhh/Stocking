package stocking.data_impl;

import org.junit.Test;
import stocking.data_service.Customer_Data_Service;
import stocking.data_service.DataFactory_Data_Service;
import stocking.po.CustomerPO;

import static org.junit.Assert.*;

/**
 * Created by xjwhhh on 2017/5/23.
 */
public class Customer_Data_ImplTest {
    DataFactory_Data_Service dataFactory_data_ = DataFactory_Data_Impl.getInstance();
    Customer_Data_Service customer_data_ = dataFactory_data_.customer();

    @Test
    public void testlogin() {
        CustomerPO customerPO = new CustomerPO("1", "456", "23456", "");
        CustomerPO newCustomer = customer_data_.execute("login", customerPO);
        CustomerPO newCustomer1 = new CustomerPO("1", "456", "23456", "");
        assertEquals(newCustomer1, newCustomer);
    }

    @Test
    public void testsignUp() {
        CustomerPO customerPO = new CustomerPO("", "123", "123456", "");
        CustomerPO newCustomer = customer_data_.execute("signUp", customerPO);
        CustomerPO newCustomer1 = new CustomerPO("34", "123", "123456", "");
        assertEquals(newCustomer1, newCustomer);
    }

    @Test
    public void testmodify() {
        CustomerPO customerPO = new CustomerPO("1", "456", "23456", "23456");
        CustomerPO newCustomer = customer_data_.execute("modify", customerPO);
        CustomerPO newCustomer1 = new CustomerPO("1", "456", "23456", "23456");
        assertEquals(newCustomer1, newCustomer);
    }
}