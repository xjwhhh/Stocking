package stocking.data_impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import stocking.data_service.BGraph_Data_Service;
import stocking.data_service.DataFactory_Data_Service;
import stocking.po.BGraphPO;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by xjwhhh on 2017/6/2.
 */
public class BGraph_Data_ImplTest {

    DataFactory_Data_Service dataFactory_data_service;
    BGraph_Data_Service bGraph_data_service;
    SimpleDateFormat dateFormat;

    @Before
    public void init() throws Exception {
        dataFactory_data_service = DataFactory_Data_Impl.getInstance();
        bGraph_data_service = dataFactory_data_service.bGraph();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * 动量策略，持有期，非板块
     *
     * @throws Exception
     */
    @Test
    public void get1() throws Exception {
        Date startDate = dateFormat.parse("2016-03-01");
        Date endDate = dateFormat.parse("2016-06-01");
        String[] array = new String[]{"000001", "000002", "000004", "300001", "300002", "300003"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        BGraphPO bGraphPO = bGraph_data_service.get("1", startDate, endDate, "1", 20, "0", jsonArray);
        JSONObject json = JSONObject.fromObject(bGraphPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"overProfit\":[0.00153042382485,0.00245095453935,-0.013344024113,-0.00719186560694,0.0193928664127,-0.0324566187367,-0.0277254356229,-0.0261116582535,0.00131311777653,-0.00731394767521,-0.0526354520284,-0.0106093186716,-0.0126902767066,-0.0225628443885,-0.0275297952085,-0.0173239889828,-0.00994540571857,0.00258717758319,7.41267417465E-4,0.0066707943195,0.0114233560521,-0.00216282650144,-0.00294042771672,-0.0110643516037,0.00390764153217,-0.00325411685732,-0.016626255806,-0.0164939627442,-0.0246471901136],\"winChance\":[0.5,0.5,0.3333333333333333,0.6666666666666666,0.6666666666666666,0,0,0,0.3333333333333333,0.3333333333333333,0,0.3333333333333333,0.6666666666666666,0.3333333333333333,0.3333333333333333,0.3333333333333333,0.3333333333333333,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.3333333333333333,0.5,0.5,0.5,0.5,0,0,0]}";
        assertEquals(needStr, str);
    }

    /**
     * 动量策略，形成期，非板块
     *
     * @throws Exception
     */
    @Test
    public void get2() throws Exception {
        Date startDate = dateFormat.parse("2016-03-01");
        Date endDate = dateFormat.parse("2016-06-01");
        String[] array = new String[]{"000001", "000002", "000004", "300001", "300002", "300003"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        BGraphPO bGraphPO = bGraph_data_service.get("1", startDate, endDate, "0", 20, "0", jsonArray);
        JSONObject json = JSONObject.fromObject(bGraphPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"overProfit\":[-4.37380149436E-4,0.00232173341682,0.00182791340799,0.00348639902185,0.0128407187482,0.00718190285803,2.9482981757E-4,0.00600871312755,4.07881007079E-4,-0.016994493176,0.00847958356395,-0.00121272069911,-0.00772288105791,-0.0153239576338,9.25239688504E-4,-0.00788173396749,0.0137695108909,-0.00581996206123,7.41267417465E-4,0.00618438861017,-0.0152418808872,-0.00548166172781,-0.00589969116521,-0.00479937074287,-0.0104144615565,-0.0237845135421,-0.0298974484304,-0.0325965433671,-0.0353030439144],\"winChance\":[0.45454545454545453,0.5333333333333333,0.5454545454545454,0.5555555555555556,0.75,0.8571428571428571,0.3333333333333333,0.8,0.4,0.5,0.5,0.5,0.5,0.3333333333333333,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.3333333333333333,0.6666666666666666,1,0,0,0,0,0.5,0,0,0,0]}";
        assertEquals(needStr, str);
    }


    /**
     * 均值策略，持有期，非板块
     *
     * @throws Exception
     */
    @Test
    public void get3() throws Exception {
        Date startDate = dateFormat.parse("2016-03-01");
        Date endDate = dateFormat.parse("2016-06-01");
        String[] array = new String[]{"000001", "000002", "000004", "000005", "000006", "000007", "000008", "000009", "000010", "000011", "000012", "000014", "000016", "000017", "000018", "000019", "000020", "000021", "000022", "000023", "000025", "000026"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        BGraphPO bGraphPO = bGraph_data_service.get("2", startDate, endDate, "1", 20, "0", jsonArray);
        JSONObject json = JSONObject.fromObject(bGraphPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"overProfit\":[-0.0441148921856,-0.020834757982,0.0534690932375,-0.0496073586097,0.00143175593182,-0.0480754249645,-0.0423448413572,-0.0191601498329,-0.0089791165089,-0.00119406516514,-0.0220700155669,-0.0194121870251,-0.0343510530601,-0.0465848947093,-0.0217314279349,-0.0607617840333,-0.0717507091196,-0.045135885035,-0.046073401979,-0.0547154008443,-0.0503186872709,-0.0400929971636,-0.0660468045971,-0.0537417286543,-0.0652965414643,-0.0774636488816,-0.0452077577087,-0.0110256562196,-0.0207034013794],\"winChance\":[0,0.5,1,0.3333333333333333,0.6666666666666666,0.3333333333333333,0,0.6666666666666666,0.3333333333333333,0.3333333333333333,0,0.3333333333333333,0,0,0,0,0,0,0,0.3333333333333333,0,0.3333333333333333,0,0,0,0,0,0.5,0.5]}";
        assertEquals(needStr, str);
    }

    /**
     * 均值策略，形成期，非板块
     *
     * @throws Exception
     */
    @Test
    public void get4() throws Exception {
        Date startDate = dateFormat.parse("2016-03-01");
        Date endDate = dateFormat.parse("2016-06-01");
        String[] array = new String[]{"000001", "000002", "000004", "000005", "000006", "000007", "000008", "000009", "000010", "000011", "000012", "000014", "000016", "000017", "000018", "000019", "000020", "000021", "000022", "000023", "000025", "000026"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        BGraphPO bGraphPO = bGraph_data_service.get("2", startDate, endDate, "0", 20, "0", jsonArray);
        JSONObject json = JSONObject.fromObject(bGraphPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"overProfit\":[-0.0062130510101,-0.00751700326191,-0.0118917316964,6.06149591606E-4,-0.0118861192344,-0.0293376302333,-0.0402030547402,-0.0262474242827,6.42503356865E-4,-0.022177542415,-0.0279541268917,-0.0212836643132,-0.043618739103,-0.0688804286548,-0.0464530793015,-0.0574734639388,-0.0382570030621,-0.0540435620439,-0.046073401979,-0.0454774449319,-0.0723409549528,-0.064143945491,-0.0714024722529,-0.0639051052106,-0.0792053778568,-0.0670155423311,-0.0719203366986,-0.070232114423,-0.0563341507851],\"winChance\":[0.2727272727272727,0.4,0.36363636363636365,0.3333333333333333,0.125,0,0.3333333333333333,0,0.2,0.25,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}";
        assertEquals(needStr, str);
    }


    /**
     * 动量策略，持有期，板块
     *
     * @throws Exception
     */
    @Test
    public void get5() throws Exception {
        Date startDate = dateFormat.parse("2016-03-01");
        Date endDate = dateFormat.parse("2016-06-01");
        String[] array = new String[]{"深市B股"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        BGraphPO bGraphPO = bGraph_data_service.get("1", startDate, endDate, "1", 20, "1", jsonArray);
        JSONObject json = JSONObject.fromObject(bGraphPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"overProfit\":[0.0314901222551,0.0323040366432,0.0404744971134,0.0437405012117,0.0690012259129,0.00486158274914,-0.0246062588305,-0.0611198535494,-0.0613596126171,-0.0509457767459,-0.0447964067612,-0.0334542986273,0.0327430183909,0.0394840769373,-0.0429374166896,-0.0310795247552,0.0570492801142,-0.0448009225051,0.00598750799104,0.0246059593845,0.0202594878603,0.0319942400295,0.0742464343518,0.0644323405251,0.0484444295918,-0.0201762547809,0.00800217654984,-3.24059186979E-4,-0.0126290596207],\"winChance\":[0.5,0.75,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.3333333333333333,0,0,0.3333333333333333,0,0.6666666666666666,0.6666666666666666,0.6666666666666666,0,0,0.3333333333333333,0,0.3333333333333333,0.3333333333333333,0.3333333333333333,0.3333333333333333,0.5,0.5,0.5,0.5,0.5,0.5,0.5]}";
        assertEquals(needStr, str);
    }

    /**
     * 动量策略，形成期，板块
     *
     * @throws Exception
     */
    @Test
    public void get6() throws Exception {
        Date startDate = dateFormat.parse("2016-03-01");
        Date endDate = dateFormat.parse("2016-06-01");
        String[] array = new String[]{"深市B股"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        BGraphPO bGraphPO = bGraph_data_service.get("1", startDate, endDate, "0", 20, "1", jsonArray);
        JSONObject json = JSONObject.fromObject(bGraphPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"overProfit\":[-0.00299684580238,0.00875252437215,0.00344569762787,0.0164400017767,0.0170601433627,0.00841696494324,0.00773974319208,0.0240879882928,0.0105017623426,0.0439881819244,0.0102923846764,0.0030119404533,0.0355851802584,0.0533411316942,-0.00414820562098,0.030307760011,0.0425906838333,0.043955820261,0.00598750799104,0.021518203836,0.0679438995567,0.0719656125771,0.0806052789627,0.0751208125552,0.0652283398956,0.0508943732821,0.0225227490929,0.0134325562793,-8.63169016192E-4],\"winChance\":[0.3181818181818182,0.4666666666666667,0.5454545454545454,0.5555555555555556,0.5,0.5714285714285714,0.5,0.6,0.6,0.75,0.5,0.5,0.75,1,0.3333333333333333,0.3333333333333333,0.3333333333333333,0.3333333333333333,0.3333333333333333,0.3333333333333333,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5]}";
        assertEquals(needStr, str);
    }


    /**
     * 均值策略，持有期，板块
     *
     * @throws Exception
     */
    @Test
    public void get7() throws Exception {
        Date startDate = dateFormat.parse("2016-03-01");
        Date endDate = dateFormat.parse("2016-06-01");
        String[] array = new String[]{"深市B股"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        BGraphPO bGraphPO = bGraph_data_service.get("2", startDate, endDate, "1", 20, "1", jsonArray);
        JSONObject json = JSONObject.fromObject(bGraphPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"overProfit\":[0.0221313833812,-0.00324572970118,0.0744972614001,-0.0304596316199,0.026524204133,-0.0173505260287,-0.0464990177813,-0.0233270073148,-0.036394677585,-6.34924899142E-4,-0.0132331440685,-0.00713899528536,-1.95329796464E-4,0.0705346754239,-0.066257638474,-0.054672999431,0.0456966419751,-0.0696839191337,-0.0212893493822,0.0947063621972,0.125941137312,0.0138036533156,0.0196075575707,0.0270780800895,-0.0806126714031,-0.0643638746096,-0.067033730432,-0.054041650498,-0.0135165794206],\"winChance\":[0.25,0.25,1,0.3333333333333333,0.6666666666666666,0.3333333333333333,0.3333333333333333,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.3333333333333333,0.3333333333333333,0.3333333333333333,0.6666666666666666,0,0.3333333333333333,0.3333333333333333,0,0.3333333333333333,1,1,0.3333333333333333,0.5,0.5,0,0,0,0.5,0.5]}";
        assertEquals(needStr, str);
    }

    /**
     * 均值策略，形成期，板块
     *
     * @throws Exception
     */
    @Test
    public void get8() throws Exception {
        Date startDate = dateFormat.parse("2016-03-01");
        Date endDate = dateFormat.parse("2016-06-01");
        String[] array = new String[]{"深市B股"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        BGraphPO bGraphPO = bGraph_data_service.get("2", startDate, endDate, "0", 20, "1", jsonArray);
        JSONObject json = JSONObject.fromObject(bGraphPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"overProfit\":[0.00197771186308,0.00201013325548,-0.0147822674107,0.0116931759873,0.0176851561911,-0.00758991766723,0.00306451132674,-0.00121288098653,-0.0254032838087,0.00830132941073,0.00556222492219,-0.00212893120608,-0.0237822073969,0.105243224738,-0.0498847586027,-0.041414625459,0.0892212419824,-0.0264620080255,-0.0212893493822,0.0932623747178,0.183149511722,0.189727744458,0.1753587334,0.138148754344,0.131195290672,0.0705661752717,0.0822717000805,0.123711349254,0.166636306459],\"winChance\":[0.5454545454545454,0.4666666666666667,0.36363636363636365,0.4444444444444444,0.625,0.5714285714285714,0.3333333333333333,0.2,0.2,0.5,0.5,0.5,0.5,1,0.6666666666666666,0.3333333333333333,0.6666666666666666,0.3333333333333333,0.3333333333333333,1,1,1,1,1,1,0.5,0.5,1,1]}";
        assertEquals(needStr, str);
    }

}