package stocking.data_impl;

import org.junit.Before;
import org.junit.Test;
import stocking.data_service.DataFactory_Data_Service;
import stocking.data_service.OverallSearch_Data_Service;
import stocking.po.MarketPO;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class OverallSearch_Data_ImplTest {
    DataFactory_Data_Service dataFactory_data_service ;
    OverallSearch_Data_Service overallSearch_data_service ;
    SimpleDateFormat dateFormat ;

    @Before
    public void init(){
        dataFactory_data_service= DataFactory_Data_Impl.getInstance();
        overallSearch_data_service = dataFactory_data_service.overall();
        dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * 休息日，所有数据均为0
     * @throws Exception
     */
    @Test
    public void getMarketInfo() throws Exception {
        Date date = dateFormat.parse("2017-04-23");
        MarketPO marketPO = overallSearch_data_service.getMarketInfo(date);
        assertEquals(0, marketPO.getLimitDownNum());
    }

    /**
     * 非休息日
     * @throws Exception
     */
    @Test
    public void getMarketInfo1() throws Exception {
        Date date = dateFormat.parse("2017-03-10");
        MarketPO marketPO = overallSearch_data_service.getMarketInfo(date);
        assertEquals(0, marketPO.getLimitDownNum());
    }

}