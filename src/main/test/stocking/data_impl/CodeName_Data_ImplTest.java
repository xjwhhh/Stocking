package stocking.data_impl;

import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import stocking.data_service.BGraph_Data_Service;
import stocking.data_service.CodeName_Data_Service;
import stocking.data_service.DataFactory_Data_Service;
import stocking.po.StockInfoPO;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * Created by xjwhhh on 2017/6/5.
 */
public class CodeName_Data_ImplTest {
    DataFactory_Data_Service dataFactory_data_service;
    CodeName_Data_Service codeName_data_service;

    @Before
    public void init() throws Exception {
        dataFactory_data_service = DataFactory_Data_Impl.getInstance();
        codeName_data_service = dataFactory_data_service.codeName();
    }

    @Test
    public void get() throws Exception {
        StockInfoPO stockInfoPO = codeName_data_service.get();
        int size = stockInfoPO.getCode().length;
        assertEquals(3472, size);
    }
}