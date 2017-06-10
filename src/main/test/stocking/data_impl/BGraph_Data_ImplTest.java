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
        String needStr="{\"overProfit\":[-0.0246471901136,-4.81389349549E-4],\"winChance\":[0,0]}";
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
        String needStr="{\"overProfit\":[-0.0353030439144,-3.56821928468E-4],\"winChance\":[0,0]}";
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
        String needStr="{\"overProfit\":[-0.0207034013794,-0.0452059151346],\"winChance\":[0.5,0]}";
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
        String needStr="{\"overProfit\":[-0.0563341507851,-0.130881257786],\"winChance\":[0,0]}";
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
        String needStr="{\"overProfit\":[-0.117022595042,0.105352957785],\"winChance\":[0,1]}";
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
        String needStr="{\"overProfit\":[-0.107111543147,-0.0694730981619],\"winChance\":[0,0]}";
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
        String needStr="{\"overProfit\":[0.0551772726117,0.0298498073209],\"winChance\":[1,1]}";
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
        String needStr="{\"overProfit\":[0.0271412953311,0.0481947966045],\"winChance\":[1,1]}";
        assertEquals(needStr, str);
    }
}