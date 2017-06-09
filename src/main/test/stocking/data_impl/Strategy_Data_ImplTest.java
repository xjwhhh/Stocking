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
        String[] array = new String[]{ "上证50"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        StrategyPO strategyPO=strategy_data_service.traceBack("1",startDate,endDate,10,20,"1",jsonArray);
        JSONObject json = JSONObject.fromObject(strategyPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"alpha\":-3.40620087582,\"annualReturn\":-0.543536505762,\"basicAnnualReturn\":0.00233187723155,\"basicProfits\":[0.0199538597021,-0.0446985271936,0.025304318027],\"beta\":-0.909857363821,\"dates\":[{\"date\":12,\"day\":2,\"hours\":0,\"minutes\":0,\"month\":3,\"seconds\":0,\"time\":1460390400000,\"timezoneOffset\":-480,\"year\":116},{\"date\":11,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1462896000000,\"timezoneOffset\":-480,\"year\":116},{\"date\":1,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":5,\"seconds\":0,\"time\":1464710400000,\"timezoneOffset\":-480,\"year\":116}],\"maxDrawDown\":0.0740759465827,\"profits\":[-0.0492113564669,-0.00358072916667,-0.0776566757493],\"sets\":[{\"codes\":[\"600518\",\"600999\"],\"names\":[\"康美药业\",\"招商证券\"],\"profits\":[-0.0492113564669,0.0915982312066]},{\"codes\":[\"600547\",\"600100\"],\"names\":[\"山东黄金\",\"同方股份\"],\"profits\":[-0.00358072916667,0.0783269961977]},{\"codes\":[\"600029\",\"600100\"],\"names\":[\"南方航空\",\"同方股份\"],\"profits\":[-0.0776566757493,0.0214621059691]}],\"sharpeRatio\":-29.0566418972}";
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
        String[] array = new String[]{ "上证50"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        StrategyPO strategyPO=strategy_data_service.traceBack("2",startDate,endDate,10,20,"1",jsonArray);
        JSONObject json = JSONObject.fromObject(strategyPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"alpha\":-1.29884006506,\"annualReturn\":0.612644544747,\"basicAnnualReturn\":0.00233187723155,\"basicProfits\":[0.0199538597021,-0.0446985271936,0.025304318027],\"beta\":-0.274750195691,\"dates\":[{\"date\":12,\"day\":2,\"hours\":0,\"minutes\":0,\"month\":3,\"seconds\":0,\"time\":1460390400000,\"timezoneOffset\":-480,\"year\":116},{\"date\":11,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":4,\"seconds\":0,\"time\":1462896000000,\"timezoneOffset\":-480,\"year\":116},{\"date\":1,\"day\":3,\"hours\":0,\"minutes\":0,\"month\":5,\"seconds\":0,\"time\":1464710400000,\"timezoneOffset\":-480,\"year\":116}],\"maxDrawDown\":0.0298172964655,\"profits\":[0.0560743546824,0.0603888162611,0.0305715197957],\"sets\":[{\"codes\":[\"600519\",\"601198\",\"600893\",\"600999\",\"600547\",\"601318\",\"600518\",\"600100\",\"600030\"],\"names\":[\"贵州茅台\",\"东兴证券\",\"航发动力\",\"招商证券\",\"山东黄金\",\"中国平安\",\"康美药业\",\"同方股份\",\"中信证券\"],\"profits\":[0.0560743546824,0.234585741811,0.0586944596818,0.0915982312066,0.163657318479,0.00616083009079,-0.0492113564669,0.158033362599,0.0829581993569]},{\"codes\":[\"600519\",\"600547\",\"600958\",\"601088\",\"600100\",\"601628\",\"601336\",\"601198\",\"600837\"],\"names\":[\"贵州茅台\",\"山东黄金\",\"东方证券\",\"中国神华\",\"同方股份\",\"中国人寿\",\"新华保险\",\"东兴证券\",\"海通证券\"],\"profits\":[0.0603888162611,-0.00358072916667,-0.133577789419,-0.113170086036,0.0783269961977,-0.123966942149,-0.0408214375157,-0.175782723501,-0.0310344827586]},{\"codes\":[\"600519\",\"600893\",\"601198\",\"601688\",\"601788\",\"600111\",\"601601\",\"600999\",\"600100\"],\"names\":[\"贵州茅台\",\"航发动力\",\"东兴证券\",\"华泰证券\",\"光大证券\",\"北方稀土\",\"中国太保\",\"招商证券\",\"同方股份\"],\"profits\":[0.0305715197957,0.0133254367782,0.0746472462449,0.13686249193,0.0713367609254,0.184566428003,0.016796875,0.0472751149048,0.0214621059691]}],\"sharpeRatio\":-28.5926209309}";
        assertEquals(needStr,str);
    }


}