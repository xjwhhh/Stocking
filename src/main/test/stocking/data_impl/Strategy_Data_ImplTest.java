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
        String needStr="{\"alpha\":0.159857520195,\"annualReturn\":0.168935087899,\"basicAnnualReturn\":0.152521115692,\"basicProfits\":[0.0751225394078,-0.0830999305554,0.0445824589138],\"beta\":1.10645328076,\"dates\":[{\"date\":12,\"day\":2,\"hours\":0,\"minutes\":0,\"month\":3,\"seconds\":0,\"time\":1460390400000,\"timezoneOffset\":-480,\"year\":116},{\"date\":11,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1462896000000,\"timezoneOffset\":-480,\"year\":116},{\"date\":1,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":5,\"seconds\":0,\"time\":1464710400000,\"timezoneOffset\":-480,\"year\":116}],\"maxDrawDown\":0.198874680579,\"profits\":[0.114427860697,-0.0844468198824,0.0105633802817],\"sets\":[{\"codes\":[\"300002\",\"000001\"],\"names\":[\"神州泰岳\",\"平安银行\"],\"profits\":[0.114427860697,0.0342384887839]},{\"codes\":[\"300003\",\"300002\"],\"names\":[\"乐普医疗\",\"神州泰岳\"],\"profits\":[-0.0844468198824,-0.124312431243]},{\"codes\":[\"000001\",\"300003\"],\"names\":[\"平安银行\",\"乐普医疗\"],\"profits\":[0.0105633802817,0.0365005793743]}],\"sharpeRatio\":-18.0543640065}";
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
        String needStr="{\"alpha\":0.48922279149,\"annualReturn\":-0.0896817085889,\"basicAnnualReturn\":0.0225572477724,\"basicProfits\":[0.0675033735612,-0.0914331935903,0.0293435594944],\"beta\":1.40709648272,\"dates\":[{\"date\":12,\"day\":2,\"hours\":0,\"minutes\":0,\"month\":3,\"seconds\":0,\"time\":1460390400000,\"timezoneOffset\":-480,\"year\":116},{\"date\":11,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1462896000000,\"timezoneOffset\":-480,\"year\":116},{\"date\":1,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":5,\"seconds\":0,\"time\":1464710400000,\"timezoneOffset\":-480,\"year\":116}],\"maxDrawDown\":0.248135532989,\"profits\":[0.121081081081,-0.127054451908,-0.0155502392344],\"sets\":[{\"codes\":[\"000008\",\"000025\",\"000019\"],\"names\":[\"神州高铁\",\"特力A\",\"深深宝A\"],\"profits\":[0.121081081081,0.0599633811413,0.0333625987709]},{\"codes\":[\"000025\",\"000018\",\"000014\"],\"names\":[\"特力A\",\"神州长城\",\"沙河股份\"],\"profits\":[-0.127054451908,-0.0582137161085,-0.0879461976203]},{\"codes\":[\"000025\",\"000014\",\"000020\"],\"names\":[\"特力A\",\"沙河股份\",\"深华发A\"],\"profits\":[-0.0155502392344,0.0364257256688,0.00148662041625]}],\"sharpeRatio\":-14.8158455423}";
        assertEquals(needStr,str);
    }


    /**
     * 动量策略，板块
     * @throws Exception
     */
    @Test
    public void traceBack3() throws Exception {
        Date startDate=dateFormat.parse("2016-03-01");
        Date endDate=dateFormat.parse("2016-06-01");
        String[] array = new String[]{"深市B股"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        StrategyPO strategyPO=strategy_data_service.traceBack("1",startDate,endDate,10,20,"1",jsonArray);
        JSONObject json = JSONObject.fromObject(strategyPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"alpha\":-0.222587085062,\"annualReturn\":-0.746799744705,\"basicAnnualReturn\":0.0201954130084,\"basicProfits\":[0.0534353174328,-0.0726209647743,0.0240325464635],\"beta\":1.36789186724,\"dates\":[{\"date\":12,\"day\":2,\"hours\":0,\"minutes\":0,\"month\":3,\"seconds\":0,\"time\":1460390400000,\"timezoneOffset\":-480,\"year\":116},{\"date\":11,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1462896000000,\"timezoneOffset\":-480,\"year\":116},{\"date\":1,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":5,\"seconds\":0,\"time\":1464710400000,\"timezoneOffset\":-480,\"year\":116}],\"maxDrawDown\":0.147505804809,\"profits\":[-0.0246835443038,-0.172189349112,0.017640954687],\"sets\":[{\"codes\":[\"200581\",\"200045\"],\"names\":[\"苏威孚B\",\"深纺织B\"],\"profits\":[-0.0246835443038,0.0521739130435]},{\"codes\":[\"200054\",\"200055\"],\"names\":[\"建摩B\",\"方大B\"],\"profits\":[-0.172189349112,0.0118483412322]},{\"codes\":[\"200596\",\"200418\"],\"names\":[\"古井贡B\",\"小天鹅B\"],\"profits\":[0.017640954687,0.116954143879]}],\"sharpeRatio\":-15.451430534}";
        assertEquals(needStr,str);
    }

    /**
     * 均值策略，板块
     * @throws Exception
     */
    @Test
    public void traceBack4() throws Exception {
        Date startDate=dateFormat.parse("2016-03-01");
        Date endDate=dateFormat.parse("2016-06-01");
        String[] array = new String[]{ "深市B股"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        StrategyPO strategyPO=strategy_data_service.traceBack("2",startDate,endDate,10,20,"1",jsonArray);
        JSONObject json = JSONObject.fromObject(strategyPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"alpha\":2.5977301193,\"annualReturn\":-0.434738056804,\"basicAnnualReturn\":0.0201954130084,\"basicProfits\":[0.0534353174328,-0.0726209647743,0.0240325464635],\"beta\":3.06288290762,\"dates\":[{\"date\":12,\"day\":2,\"hours\":0,\"minutes\":0,\"month\":3,\"seconds\":0,\"time\":1460390400000,\"timezoneOffset\":-480,\"year\":116},{\"date\":11,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1462896000000,\"timezoneOffset\":-480,\"year\":116},{\"date\":1,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":5,\"seconds\":0,\"time\":1464710400000,\"timezoneOffset\":-480,\"year\":116}],\"maxDrawDown\":0.352652561177,\"profits\":[0.075873827792,-0.276778733385,0.0965677719604],\"sets\":[{\"codes\":[\"200025\",\"200581\",\"200869\",\"200152\",\"200596\",\"200058\"],\"names\":[\"特力B\",\"苏威孚B\",\"张裕B\",\"山航B\",\"古井贡B\",\"深赛格B\"],\"profits\":[0.075873827792,-0.0246835443038,0.044993383326,0.103202846975,0.0712478920742,0.156746031746]},{\"codes\":[\"200025\",\"200054\",\"200418\",\"200018\",\"200152\",\"200550\"],\"names\":[\"特力B\",\"建摩B\",\"小天鹅B\",\"神州B\",\"山航B\",\"江铃B\"],\"profits\":[-0.276778733385,-0.172189349112,0.176412776413,-0.0718446601942,-0.0331905781585,-0.127751196172]},{\"codes\":[\"200025\",\"200596\",\"200054\",\"200550\",\"200152\",\"200020\"],\"names\":[\"特力B\",\"古井贡B\",\"建摩B\",\"江铃B\",\"山航B\",\"深华发B\"],\"profits\":[0.0965677719604,0.017640954687,-0.0150322118826,-0.018302828619,-0.029197080292,-0.01]}],\"sharpeRatio\":-8.7790742816}";
        assertEquals(needStr,str);
    }


}