package stocking.data_impl;

import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import stocking.data_service.DataFactory_Data_Service;
import stocking.data_service.SingleSearch_Data_Service;
import stocking.po.StockPO;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by xjwhhh on 2017/5/23.
 */
public class SingleSearch_Data_ImplTest {
    DataFactory_Data_Service dataFactory_data_service;
    SingleSearch_Data_Service singleSearch_data_service;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Method method_isInteger;
    Method method_getSectionByCode;

    @Before
    public void init() throws Exception {
        dataFactory_data_service = DataFactory_Data_Impl.getInstance();
        singleSearch_data_service = dataFactory_data_service.singleSearch();
        method_getSectionByCode = SingleSearch_Data_Impl.class.getDeclaredMethod("getSectionByCode", String.class);
        method_getSectionByCode.setAccessible(true);
    }


    /**
     * 不存在的股票代码
     *
     * @throws Exception
     */
    @Test
    public void getStockList1() throws Exception {
        Date myDate1 = dateFormat.parse("2017-05-12");
        Date myDate2 = dateFormat.parse("2017-05-17");
        StockPO stockPO = singleSearch_data_service.getStockList("300060", myDate1, myDate2);
        assertEquals(true, stockPO == null);
    }

    /**
     * 不存在的股票名
     *
     * @throws Exception
     */
    @Test
    public void getStockList2() throws Exception {
        Date myDate1 = dateFormat.parse("2017-05-12");
        Date myDate2 = dateFormat.parse("2017-05-17");
        StockPO stockPO = singleSearch_data_service.getStockList("发VR不能", myDate1, myDate2);
        assertEquals(true, stockPO == null);
    }

    /**
     * 存在的股票代码
     *
     * @throws Exception
     */
    @Test
    public void getStockList3() throws Exception {
        Date myDate1 = dateFormat.parse("2017-05-12");
        Date myDate2 = dateFormat.parse("2017-05-17");
        StockPO stockPO = singleSearch_data_service.getStockList("000001", myDate1, myDate2);
        JSONObject json = JSONObject.fromObject(stockPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr = "{\"adjClose\":[8.9,8.86,8.84,8.77],\"average10\":[0,0,0,0],\"average20\":[0,0,0,0],\"average30\":[0,0,0,0],\"average5\":[0,0,0,0],\"average60\":[0,0,0,0],\"code\":\"000001\",\"dates\":[\"2017-05-12 00:00:00\",\"2017-05-15 00:00:00\",\"2017-05-16 00:00:00\",\"2017-05-17 00:00:00\"],\"high\":[8.9,8.95,8.85,8.81],\"highest\":8.95,\"low\":[8.64,8.81,8.73,8.75],\"lowest\":8.64,\"name\":\"平安银行\",\"open\":[8.68,8.89,8.84,8.81],\"over\":\"2017-05-17\",\"start\":\"2017-05-12\",\"up\":0.012571428571428506,\"variance\":8.214730517016192E-6,\"volume\":[91796819,53657821,52487233,41733802]}";
        assertEquals(needStr, str);
    }

    /**
     * 存在的股票名
     *
     * @throws Exception
     */
    @Test
    public void getStockList4() throws Exception {
        Date myDate1 = dateFormat.parse("2017-05-12");
        Date myDate2 = dateFormat.parse("2017-05-17");
        StockPO stockPO = singleSearch_data_service.getStockList("平安银行", myDate1, myDate2);
        JSONObject json = JSONObject.fromObject(stockPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr = "{\"adjClose\":[8.9,8.86,8.84,8.77],\"average10\":[0,0,0,0],\"average20\":[0,0,0,0],\"average30\":[0,0,0,0],\"average5\":[0,0,0,0],\"average60\":[0,0,0,0],\"code\":\"000001\",\"dates\":[\"2017-05-12 00:00:00\",\"2017-05-15 00:00:00\",\"2017-05-16 00:00:00\",\"2017-05-17 00:00:00\"],\"high\":[8.9,8.95,8.85,8.81],\"highest\":8.95,\"low\":[8.64,8.81,8.73,8.75],\"lowest\":8.64,\"name\":\"平安银行\",\"open\":[8.68,8.89,8.84,8.81],\"over\":\"2017-05-17\",\"start\":\"2017-05-12\",\"up\":0.012571428571428506,\"variance\":8.214730517016192E-6,\"volume\":[91796819,53657821,52487233,41733802]}";
        assertEquals(needStr, str);
    }


}