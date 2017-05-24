package stocking.data_impl;

import org.junit.Test;
import stocking.po.StockPO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by xjwhhh on 2017/5/23.
 */
public class SingleSearch_Data_ImplTest {

    //不存在的股票代码
    @Test
    public void getStockList1() throws Exception {
        SingleSearch_Data_Impl singleSearch_data_ = new SingleSearch_Data_Impl();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate1 = dateFormat1.parse("2017-05-12");
        Date myDate2 = dateFormat1.parse("2017-05-17");
        StockPO stockPO = singleSearch_data_.getStockList("300060", myDate1, myDate2);
        assertEquals(true, stockPO == null);
    }

    //不存在的股票名
    @Test
    public void getStockList2() throws Exception {
        SingleSearch_Data_Impl singleSearch_data_ = new SingleSearch_Data_Impl();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate1 = dateFormat1.parse("2017-05-12");
        Date myDate2 = dateFormat1.parse("2017-05-17");
        StockPO stockPO = singleSearch_data_.getStockList("发VR不能", myDate1, myDate2);
        assertEquals(true, stockPO == null);

    }


}