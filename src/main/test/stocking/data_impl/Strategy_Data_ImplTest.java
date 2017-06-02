package stocking.data_impl;

import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import stocking.data_service.DataFactory_Data_Service;
import stocking.data_service.Strategy_Data_Service;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import net.sf.json.JSONArray;
import stocking.po.StrategyPO;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class Strategy_Data_ImplTest {
    DataFactory_Data_Service dataFactory_data_service;
    Strategy_Data_Service strategy_data_service;
    SimpleDateFormat dateFormat ;

    @Before
    public void init() throws Exception{
        dataFactory_data_service=DataFactory_Data_Impl.getInstance();
        strategy_data_service=dataFactory_data_service.strategy();
        dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    }


    /**
     * 动量策略，非板块
     * @throws Exception
     */
    @Test
    public void traceBack1() throws Exception {
        Date startDate=dateFormat.parse("2016-03-01");
        Date endDate=dateFormat.parse("2016-06-01");
        String[] array = new String[]{"000001", "000002", "000004","300001","300002","300003"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        StrategyPO strategyPO=strategy_data_service.traceBack("1",startDate,endDate,10,20,"0",jsonArray);
        JSONObject json = JSONObject.fromObject(strategyPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"alpha\":0.159857520195,\"annualReturn\":0.168935087899,\"basicAnnualReturn\":0.152521115692,\"basicProfits\":[0.0751225394078,-0.0830999305554,0.0445824589138],\"beta\":1.10645328076,\"dates\":[{\"date\":12,\"day\":2,\"hours\":0,\"minutes\":0,\"month\":3,\"seconds\":0,\"time\":1460390400000,\"timezoneOffset\":-480,\"year\":116},{\"date\":11,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1462896000000,\"timezoneOffset\":-480,\"year\":116},{\"date\":1,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":5,\"seconds\":0,\"time\":1464710400000,\"timezoneOffset\":-480,\"year\":116}],\"maxDrawDown\":0.198874680579,\"profits\":[0.114427860697,-0.0844468198824,0.0105633802817],\"sets\":[{\"codes\":[\"300002\",\"000001\"],\"names\":[\"神州泰岳\",\"平安银行\"],\"profits\":[0.114428,0.034238]},{\"codes\":[\"300003\",\"300002\"],\"names\":[\"乐普医疗\",\"神州泰岳\"],\"profits\":[-0.084447,-0.124312]},{\"codes\":[\"000001\",\"300003\"],\"names\":[\"平安银行\",\"乐普医疗\"],\"profits\":[0.010563,0.036501]}],\"sharpeRatio\":-18.0543640065}";
        assertEquals(needStr,str);
    }

    /**
     * 均值策略，非板块
     * @throws Exception
     */
    @Test
    public void traceBack2() throws Exception {
        Date startDate=dateFormat.parse("2016-03-01");
        Date endDate=dateFormat.parse("2016-06-01");
        String[] array = new String[]{"000001","000002", "000004", "000005", "000006", "000007", "000008", "000009", "000010", "000011", "000012", "000014", "000016", "000017", "000018", "000019", "000020", "000021", "000022", "000023", "000025", "000026"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        StrategyPO strategyPO=strategy_data_service.traceBack("2",startDate,endDate,10,20,"0",jsonArray);
        JSONObject json = JSONObject.fromObject(strategyPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        System.out.print(str);
        String needStr="{\"alpha\":0.48922279149,\"annualReturn\":-0.0896817085889,\"basicAnnualReturn\":0.0225572477724,\"basicProfits\":[0.0675033735612,-0.0914331935903,0.0293435594944],\"beta\":1.40709648272,\"dates\":[{\"date\":12,\"day\":2,\"hours\":0,\"minutes\":0,\"month\":3,\"seconds\":0,\"time\":1460390400000,\"timezoneOffset\":-480,\"year\":116},{\"date\":11,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1462896000000,\"timezoneOffset\":-480,\"year\":116},{\"date\":1,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":5,\"seconds\":0,\"time\":1464710400000,\"timezoneOffset\":-480,\"year\":116}],\"maxDrawDown\":0.248135532989,\"profits\":[0.121081081081,-0.127054451908,-0.0155502392344],\"sets\":[{\"codes\":[\"000008\",\"000025\",\"000019\"],\"names\":[\"神州高铁\",\"特力A\",\"深深宝A\"],\"profits\":[0.121081,0.059963,0.033363]},{\"codes\":[\"000025\",\"000018\",\"000014\"],\"names\":[\"特力A\",\"神州长城\",\"沙河股份\"],\"profits\":[-0.127054,-0.058214,-0.087946]},{\"codes\":[\"000025\",\"000014\",\"000020\"],\"names\":[\"特力A\",\"沙河股份\",\"深华发A\"],\"profits\":[-0.01555,0.036426,0.001487]}],\"sharpeRatio\":-14.8158455423}";
        assertEquals(needStr,str);
    }

    //TODO 板块，数据有异常，太大了


}