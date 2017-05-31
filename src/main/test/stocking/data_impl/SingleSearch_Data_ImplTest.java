package stocking.data_impl;

import org.junit.Test;
import stocking.data_service.DataFactory_Data_Service;
import stocking.data_service.SingleSearch_Data_Service;
import stocking.po.StockPO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by xjwhhh on 2017/5/23.
 */
public class SingleSearch_Data_ImplTest {
    DataFactory_Data_Service dataFactory_data_service = DataFactory_Data_Impl.getInstance();
    SingleSearch_Data_Service singleSearch_data_service = dataFactory_data_service.singleSearch();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //不存在的股票代码
    @Test
    public void getStockList1() throws Exception {
        Date myDate1 = dateFormat.parse("2017-05-12");
        Date myDate2 = dateFormat.parse("2017-05-17");
        StockPO stockPO = singleSearch_data_service.getStockList("300060", myDate1, myDate2);
        assertEquals(true, stockPO == null);
    }

    //不存在的股票名
    @Test
    public void getStockList2() throws Exception {
        Date myDate1 = dateFormat.parse("2017-05-12");
        Date myDate2 = dateFormat.parse("2017-05-17");
        StockPO stockPO = singleSearch_data_service.getStockList("发VR不能", myDate1, myDate2);
        assertEquals(true, stockPO == null);
    }


}