package stocking.data_impl;

import org.junit.Test;
import stocking.po.CustomerPO;

import static org.junit.Assert.*;

/**
 * Created by xjwhhh on 2017/5/23.
 */
public class Customer_Data_ImplTest {
    @Test
    public void testlogin(){
        Customer_Data_Impl customer_data_=new Customer_Data_Impl();
        CustomerPO customerPO=new CustomerPO("1","456","23456","");
        CustomerPO newCustomer=customer_data_.execute("login",customerPO);
        CustomerPO newCustomer1=new CustomerPO("1","456","23456","");
        assertEquals(newCustomer1,newCustomer);
    }

    @Test
    public void testsignUp(){
        Customer_Data_Impl customer_data_=new Customer_Data_Impl();
        CustomerPO customerPO=new CustomerPO("","123","123456","");
        CustomerPO newCustomer=customer_data_.execute("signUp",customerPO);
        CustomerPO newCustomer1=new CustomerPO("34","123","123456","");
        assertEquals(newCustomer1,newCustomer);
    }

    @Test
    public void testmodify(){
        Customer_Data_Impl customer_data_=new Customer_Data_Impl();
        CustomerPO customerPO=new CustomerPO("1","456","23456","23456");
        CustomerPO newCustomer=customer_data_.execute("modify",customerPO);
        CustomerPO newCustomer1=new CustomerPO("1","456","23456","23456");
        assertEquals(newCustomer1,newCustomer);
    }
}