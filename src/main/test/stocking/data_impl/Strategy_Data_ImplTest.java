package stocking.data_impl;

import org.junit.Before;
import org.junit.Test;
import stocking.data_service.DataFactory_Data_Service;
import stocking.data_service.Strategy_Data_Service;
import java.lang.reflect.Method;
import net.sf.json.JSONArray;

import static org.junit.Assert.*;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class Strategy_Data_ImplTest {
    DataFactory_Data_Service dataFactory_data_service;
    Strategy_Data_Service strategy_data_service;

    @Before
    public void init() throws Exception{
        dataFactory_data_service=DataFactory_Data_Impl.getInstance();
        strategy_data_service=dataFactory_data_service.strategy();
    }



    @Test
    public void traceBack() throws Exception {
    }

}