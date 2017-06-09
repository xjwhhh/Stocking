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
        String[] array = new String[]{"上证50"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        BGraphPO bGraphPO = bGraph_data_service.get("1", startDate, endDate, "1", 20, "1", jsonArray);
        JSONObject json = JSONObject.fromObject(bGraphPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"overProfit\":[-0.0391871851162,0.00140183128052,-0.00506003761387,-0.00570786397495,-0.00374818930951,-0.0509211418594,-0.0414592933148,-0.0497198107092,-0.0436694706395,-0.00263432478491,0.0124920876811,0.0307227077352,0.0256847904954,0.0380102744695,-0.00905870742365,0.00278600748318,0.0342782397378,-0.00145944114081,-0.00597748516512,-0.011327878162,-0.0144308101622,-0.0419365725822,-0.0702098383527,-0.0649044824189,-0.0570920438465,-0.0398812061597,-0.0202695218624,-0.0269848760277,-0.117022595042],\"winChance\":[0.25,0.5,0.3333333333333333,0.3333333333333333,0.3333333333333333,0,0.3333333333333333,0,0.3333333333333333,0.3333333333333333,0.3333333333333333,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.3333333333333333,0.3333333333333333,0,0,0,0,0.5,0.5,0]}";
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
        String[] array = new String[]{"上证50"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        BGraphPO bGraphPO = bGraph_data_service.get("1", startDate, endDate, "0", 20, "1", jsonArray);
        JSONObject json = JSONObject.fromObject(bGraphPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"overProfit\":[-0.00365382729288,0.0187790927287,-0.0246495870766,-0.0129861534434,0.0150890018693,0.0114552757635,-0.00961012640366,0.0202261791074,-0.0273755940987,0.0081764652746,0.00623447766697,0.00316968411608,0.00185356470485,-0.00928538336449,-0.0252157470596,0.0392789485631,0.0372030322893,-0.00591093186211,-0.00597748516512,0.00209539741542,-0.0407374930017,-0.0656931899781,-0.0470155617998,-0.0502631174906,-0.0530300881772,-0.0686769083574,-0.0821645044935,-0.108356008566,-0.107111543147],\"winChance\":[0.4090909090909091,0.6666666666666666,0.2727272727272727,0.3333333333333333,0.5,0.7142857142857143,0.5,0.6,0.4,0.5,0.75,0.5,0.75,0.6666666666666666,0.3333333333333333,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.6666666666666666,0,0,0,0,0,0,0,0,0]}";
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
        String[] array = new String[]{"上证50"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        BGraphPO bGraphPO = bGraph_data_service.get("2", startDate, endDate, "1", 20, "1", jsonArray);
        JSONObject json = JSONObject.fromObject(bGraphPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"overProfit\":[0.00893601826887,-0.00341517828637,0.0404755321168,0.0247630135291,0.0043855922437,-9.81105915369E-4,0.00358516906847,0.0186036203519,0.0488250134012,0.0549001456207,0.0417607662385,0.0330925443109,0.0479255809121,0.0364919309657,0.0353550696516,-0.00585221971525,-0.0187683501806,-0.024663773438,0.0179572991259,-0.0186751115987,-0.00836041292572,0.0206539444434,0.0500704513983,0.0164862992161,0.0195205106633,0.0190057706388,0.0205872379868,0.03118131941,0.0551772726117],\"winChance\":[0.75,0.75,1,1,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.6666666666666666,1,1,1,0.6666666666666666,1,1,1,0.3333333333333333,0.6666666666666666,0.3333333333333333,0.6666666666666666,0.3333333333333333,0.6666666666666666,1,1,1,1,1,0.5,0.5,1]}";
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
        String[] array = new String[]{"上证50"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        BGraphPO bGraphPO = bGraph_data_service.get("2", startDate, endDate, "0", 20, "1", jsonArray);
        JSONObject json = JSONObject.fromObject(bGraphPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr="{\"overProfit\":[0.00308357236004,7.65707734473E-5,0.00749516735736,-0.00756872399518,-0.00169034564836,-0.0108851286799,0.0171790291902,-0.0194705313681,-0.00310673382036,-0.045196237937,0.0168413580171,0.0165240276367,0.00376021565827,0.00749346083775,0.0239817677892,0.00432618914176,-0.0295281214863,-0.0214163256209,0.0179572991259,-0.0206636552746,-0.0526504174086,0.0113327558246,0.0301119446438,0.0205506928651,0.0242421942577,0.0185598110877,0.0214313552317,0.0156351950068,0.0271412953311],\"winChance\":[0.6363636363636364,0.5333333333333333,0.5454545454545454,0.4444444444444444,0.5,0.42857142857142855,0.6666666666666666,0.2,0.4,0.25,0.5,0.75,0.75,0.6666666666666666,0.6666666666666666,0.6666666666666666,0.3333333333333333,0.3333333333333333,0.6666666666666666,0.3333333333333333,0,0.5,1,1,1,1,1,0.5,1]}";
        assertEquals(needStr, str);
    }

}