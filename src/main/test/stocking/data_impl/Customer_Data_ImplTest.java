package stocking.data_impl;

import org.junit.Before;
import org.junit.Test;
import stocking.data_service.Customer_Data_Service;
import stocking.data_service.DataFactory_Data_Service;
import stocking.po.CustomerPO;

import static org.junit.Assert.*;

/**
 * Created by xjwhhh on 2017/5/23.
 */
public class Customer_Data_ImplTest {
    DataFactory_Data_Service dataFactory_data_service ;
    Customer_Data_Service customer_data_service;

    @Before
    public void init(){
        dataFactory_data_service= DataFactory_Data_Impl.getInstance();
        customer_data_service = dataFactory_data_service.customer();
    }

    /**
     * 登录成功
     */
    @Test
    public void login1() {
        CustomerPO customerPO = new CustomerPO("1", "456", "23456", "");
        CustomerPO newCustomer = customer_data_service.execute("login", customerPO);
        CustomerPO newCustomer1 = new CustomerPO("1", "456", "23456", "");
        assertEquals(newCustomer1, newCustomer);
    }

    /**
     * 登录失败
     */
    @Test
    public void login2() {
        CustomerPO customerPO = new CustomerPO("1", "456", "234567", "");
        CustomerPO newCustomer = customer_data_service.execute("login", customerPO);
        CustomerPO newCustomer1 = new CustomerPO("1", "456", "23456", "");
        assertEquals(true, newCustomer==null);
    }

    /**
     * 注册
     */
    @Test
    public void signUp() {
        CustomerPO customerPO = new CustomerPO("", "123", "123456", "");
        CustomerPO newCustomer = customer_data_service.execute("signUp", customerPO);
        CustomerPO newCustomer1 = new CustomerPO("34", "123", "123456", "");
        assertEquals(newCustomer1, newCustomer);
    }

    /**
     * 修改信息成功
     */
    @Test
    public void modify1() {
        CustomerPO customerPO = new CustomerPO("1", "456", "23456", "23456");
        CustomerPO newCustomer = customer_data_service.execute("modify", customerPO);
        CustomerPO newCustomer1 = new CustomerPO("1", "456", "23456", "23456");
        assertEquals(newCustomer1, newCustomer);
    }

    /**
     * 修改信息失败
     */
    @Test
    public void modify2() {
        CustomerPO customerPO = new CustomerPO("1", "456", "234567", "23456");
        CustomerPO newCustomer = customer_data_service.execute("modify", customerPO);
        CustomerPO newCustomer1 = new CustomerPO("1", "456", "23456", "23456");
        assertEquals(true,newCustomer==null);
    }
}