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
import net.sf.json.JSONObject;

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
        String needStr="{\"adjClose\":[8.9,8.86,8.84,8.77],\"average10\":[0,0,0,0],\"average20\":[0,0,0,0],\"average30\":[0,0,0,0],\"average5\":[0,0,0,0],\"average60\":[0,0,0,0],\"code\":\"000001\",\"dates\":[{\"date\":12,\"day\":5,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1494518400000,\"timezoneOffset\":-480,\"year\":117},{\"date\":15,\"day\":1,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1494777600000,\"timezoneOffset\":-480,\"year\":117},{\"date\":16,\"day\":2,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1494864000000,\"timezoneOffset\":-480,\"year\":117},{\"date\":17,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1494950400000,\"timezoneOffset\":-480,\"year\":117}],\"high\":[8.9,8.95,8.85,8.81],\"low\":[8.64,8.81,8.73,8.75],\"name\":\"平安银行\",\"open\":[8.68,8.89,8.84,8.81],\"over\":{\"date\":17,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1494950400000,\"timezoneOffset\":-480,\"year\":117},\"start\":{\"date\":12,\"day\":5,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1494518400000,\"timezoneOffset\":-480,\"year\":117},\"variance\":8.214730517016192E-6,\"volume\":[91796819,53657821,52487233,41733802]}";
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
        String needStr="{\"adjClose\":[8.9,8.86,8.84,8.77],\"average10\":[0,0,0,0],\"average20\":[0,0,0,0],\"average30\":[0,0,0,0],\"average5\":[0,0,0,0],\"average60\":[0,0,0,0],\"code\":\"000001\",\"dates\":[{\"date\":12,\"day\":5,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1494518400000,\"timezoneOffset\":-480,\"year\":117},{\"date\":15,\"day\":1,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1494777600000,\"timezoneOffset\":-480,\"year\":117},{\"date\":16,\"day\":2,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1494864000000,\"timezoneOffset\":-480,\"year\":117},{\"date\":17,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1494950400000,\"timezoneOffset\":-480,\"year\":117}],\"high\":[8.9,8.95,8.85,8.81],\"low\":[8.64,8.81,8.73,8.75],\"name\":\"平安银行\",\"open\":[8.68,8.89,8.84,8.81],\"over\":{\"date\":17,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1494950400000,\"timezoneOffset\":-480,\"year\":117},\"start\":{\"date\":12,\"day\":5,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1494518400000,\"timezoneOffset\":-480,\"year\":117},\"variance\":8.214730517016192E-6,\"volume\":[91796819,53657821,52487233,41733802]}";
        assertEquals(needStr, str);
    }




}