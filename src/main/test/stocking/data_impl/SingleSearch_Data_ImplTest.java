package stocking.data_impl;

import org.junit.Before;
import org.junit.Test;
import stocking.data_service.DataFactory_Data_Service;
import stocking.data_service.SingleSearch_Data_Service;
import stocking.po.StockPO;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

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
        method_isInteger = SingleSearch_Data_Impl.class.getDeclaredMethod("isInteger", String.class);
        method_isInteger.setAccessible(true);
        method_getSectionByCode = SingleSearch_Data_Impl.class.getDeclaredMethod("getSectionByCode", String.class);
        method_getSectionByCode.setAccessible(true);
    }

    /**
     * 纯非数字
     *
     * @throws Exception
     */
    @Test
    public void isInteger1() throws Exception {
        String s = "sss";
        SingleSearch_Data_Impl singleSearch_data_ = new SingleSearch_Data_Impl();
        boolean x = (Boolean) method_isInteger.invoke(singleSearch_data_, s);
        assertEquals(false, x);
    }

    /**
     * 数字与非数字混合
     *
     * @throws Exception
     */
    @Test
    public void isInteger2() throws Exception {
        String s = "sss00";
        SingleSearch_Data_Impl singleSearch_data_ = new SingleSearch_Data_Impl();
        boolean x = (Boolean) method_isInteger.invoke(singleSearch_data_, s);
        assertEquals(false, x);
    }

    /**
     * 纯数字
     *
     * @throws Exception
     */
    @Test
    public void isInteger3() throws Exception {
        String s = "000001";
        SingleSearch_Data_Impl singleSearch_data_ = new SingleSearch_Data_Impl();
        boolean x = (Boolean) method_isInteger.invoke(singleSearch_data_, s);
        assertEquals(true, x);
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


}