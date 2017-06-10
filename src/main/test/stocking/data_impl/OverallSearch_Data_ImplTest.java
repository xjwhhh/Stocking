package stocking.data_impl;

import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import stocking.data_service.DataFactory_Data_Service;
import stocking.data_service.OverallSearch_Data_Service;
import stocking.po.MarketPO;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class OverallSearch_Data_ImplTest {
    DataFactory_Data_Service dataFactory_data_service;
    OverallSearch_Data_Service overallSearch_data_service;
    SimpleDateFormat dateFormat;

    @Before
    public void init() {
        dataFactory_data_service = DataFactory_Data_Impl.getInstance();
        overallSearch_data_service = dataFactory_data_service.overall();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * 休息日，所有数据均为0
     *
     * @throws Exception
     */
    @Test
    public void getMarketInfo1() throws Exception {
        Date date = dateFormat.parse("2017-04-23");
        MarketPO marketPO = overallSearch_data_service.getMarketInfo(date);
        JSONObject json = JSONObject.fromObject(marketPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr = "{\"belowFivePerNum\":0,\"limitDownNum\":0,\"limitUpNum\":0,\"numOfStock\":0,\"oc_belowMFivePerNum\":0,\"oc_overPFivePerNum\":0,\"overFivePerNum\":0,\"totalDeal\":0}";
        assertEquals(needStr, str);
    }

    /**
     * 非休息日
     *
     * @throws Exception
     */
    @Test
    public void getMarketInfo2() throws Exception {
        Date date = dateFormat.parse("2017-03-22");
        MarketPO marketPO = overallSearch_data_service.getMarketInfo(date);
        JSONObject json = JSONObject.fromObject(marketPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr = "{\"belowFivePerNum\":8,\"limitDownNum\":1,\"limitUpNum\":56,\"numOfStock\":3004,\"oc_belowMFivePerNum\":60,\"oc_overPFivePerNum\":5,\"overFivePerNum\":78,\"totalDeal\":3.7062065382E10}";
        assertEquals(needStr, str);
    }

}