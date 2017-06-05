package stocking.data_impl;

import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import stocking.data_service.CustomerCollection_Data_Service;
import stocking.data_service.DataFactory_Data_Service;
import stocking.po.CollectionPO;

import static org.junit.Assert.*;

/**
 * Created by xjwhhh on 2017/6/5.
 */
public class CustomerCollection_Data_ImplTest {
    DataFactory_Data_Service dataFactory_data_service;
    CustomerCollection_Data_Service customerCollection_data_service;

    @Before
    public void init(){
        dataFactory_data_service=DataFactory_Data_Impl.getInstance();
        customerCollection_data_service=dataFactory_data_service.customerCollection();
    }


    @Test
    public void getCollection() throws Exception {
        CollectionPO collectionPO=customerCollection_data_service.getCollection("1");
        JSONObject json = JSONObject.fromObject(collectionPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"code\":[\"000001\"],\"name\":[\"平安银行\"]}";
        assertEquals(needStr,str);
    }

}