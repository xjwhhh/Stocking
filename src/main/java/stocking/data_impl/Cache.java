package stocking.data_impl;

import net.sf.json.JSONObject;
import stocking.data_impl.dbconnector.DBConnectionManager;
import stocking.data_impl.dbconnector.DBConnectionPool;
import stocking.data_service.DataFactory_Data_Service;
import stocking.po.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xjwhhh on 2017/5/24.
 */
public class Cache {
    static private Cache cache;
    DBConnectionManager connectionManager = DBConnectionManager.getInstance();
    Hashtable pools = connectionManager.getPools();
    DBConnectionPool pool = (DBConnectionPool) pools.get("stock");
    Tools tools = Tools.getInstance();

    private Hashtable<String, String> code_name = new Hashtable<String, String>();
    private Hashtable<String, String> code_section = new Hashtable<String, String>();
    private Hashtable<String, String> code_industry = new Hashtable<String, String>();
    private Hashtable<String, String> name_code = new Hashtable<String, String>();
    private MarketPO marketPO;//默认市场情况
    private RankingPO rankingPO1;//日换手率达到20%的前五只证券
    private RankingPO rankingPO2;//日涨幅偏离值达到7%的前五只证券
    private RankingPO rankingPO3;//日跌幅偏离值达到7%的前五只证券
    private Hashtable<String, NewsPO> singleNews = new Hashtable<String, NewsPO>();
    private Hashtable<String, MinuteDataPO> minuteDataPOHashtable = new Hashtable<String, MinuteDataPO>();



    private Cache() {

        this.setStockInfo();
        this.setYesterdayMarketPO();
//        rankingPO1 = setRankingPO("日换手率达到20%的前五只证券");
//        rankingPO2 = setRankingPO("日涨幅偏离值达到7%的前五只证券");
//        rankingPO3 = setRankingPO("日跌幅偏离值达到7%的前五只证券");

//        this.getMinuteData();
//        Date date = new Date();
//        SimpleDateFormat hourFormatter = new SimpleDateFormat("HH");
//        int hour = Integer.parseInt(hourFormatter.format(date));
//        if (hour > 15) {
//            this.setMinuteData();
//        }
    }

    /**
     * 获取唯一实例
     *
     * @return
     */
    static synchronized public Cache getInstance() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    /**
     * 读取股票信息键值对
     */
    private void setStockInfo() {
        Connection connection = connectionManager.getConnection("stock");
        String sql = "select name,code,industry,section from basicinfo";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                code_name.put(rs.getString("code"), rs.getString("name"));
                code_industry.put(rs.getString("code"), rs.getString("industry"));
                code_section.put(rs.getString("code"), rs.getString("section"));
                name_code.put(rs.getString("name"), rs.getString("code"));
            }
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取市场界面默认值，15:00之前昨日市场情况，之后今日
     */
    private void setYesterdayMarketPO() {

        String[] data = new String[8];
        int i = 0;
        DataFactory_Data_Service dataFactory_data_service = DataFactory_Data_Impl.getInstance();
        Date date = new Date();
        SimpleDateFormat hourFormatter = new SimpleDateFormat("HH");
        int hour = Integer.parseInt(hourFormatter.format(date));
        Date yesterday = new Date();
//        if (hour < 15) {
            yesterday = new Date(date.getTime() - 24 * 60 * 60 * 1000);
//        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String ofWeek = dateFm.format(yesterday);
        String todayStr = formatter.format(yesterday);
        String yesterday2Str = "";
        String yesterday3Str = "";
        System.out.println(yesterday);

        if (ofWeek.equals("星期一")) {
            yesterday2Str = formatter.format(new Date(yesterday.getTime() - 3 * 24 * 60 * 60 * 1000));
            yesterday3Str = formatter.format(new Date(yesterday.getTime() - 4 * 24 * 60 * 60 * 1000));
        } else if(ofWeek.equals("星期六")){
            yesterday2Str = formatter.format(new Date(yesterday.getTime() - 24 * 60 * 60 * 1000));
            yesterday3Str = formatter.format(new Date(yesterday.getTime() - 2*24 * 60 * 60 * 1000));
        }
        else  if(ofWeek.equals("星期日")){
            yesterday2Str = formatter.format(new Date(yesterday.getTime() - 24 * 60 * 60 * 1000*2));
            yesterday3Str = formatter.format(new Date(yesterday.getTime() - 24 * 60 * 60 * 1000*3));
        }
        else{
            yesterday2Str=formatter.format(yesterday);
        }
        System.out.println(yesterday2Str);
        System.out.println(yesterday3Str);
        try {
            List<String> commands = new LinkedList<String>();
            commands.add("python");
            commands.add(tools.getProjectPath("src\\main\\java\\stocking\\python_Impl\\OverallSearch.py"));
            commands.add(yesterday2Str);
            commands.add(yesterday3Str);
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process pr = processBuilder.start();
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream(), "gbk"));
            String line;
            while ((line = in.readLine()) != null) {
                data[i] = line;
                i++;
            }
            in.close();
            double totalDeal = Double.parseDouble(data[0]);
            int limitUpNum = Integer.parseInt(data[1]);
            int limitDownNum = Integer.parseInt(data[2]);
            int overFivePerNum = Integer.parseInt(data[3]);//涨幅超过5%的股票数
            int belowFivePerNum = Integer.parseInt(data[4]);//跌幅超过5%的股票数
            int oc_overPFivePerNum = Integer.parseInt(data[5]);//开盘-收盘大于5%*上一个交易日收盘价的股票个数
            int oc_belowMFivePerNum = Integer.parseInt(data[6]);//开盘-收盘小于-5%*上一个交易日收盘价的股票个数
            int numOfStock = Integer.parseInt(data[7]);
            marketPO = new MarketPO(totalDeal, limitUpNum, limitDownNum, overFivePerNum, belowFivePerNum, oc_overPFivePerNum, oc_belowMFivePerNum, numOfStock);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取龙虎榜
     *
     * @param reason
     * @return
     */
    private RankingPO setRankingPO(String reason) {
        Date today = new Date();
        Date yesterday = new Date(today.getTime() - 24 * 60 * 60 * 1000 * 2);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String yesterdayStr = formatter.format(yesterday);
        try {
            List<String> commands = new LinkedList<String>();
            commands.add("python");
            commands.add(tools.getProjectPath("src\\main\\java\\stocking\\python_Impl\\DragonAndTigerSpider.py"));
            commands.add(yesterdayStr);
            commands.add(reason);
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process pr = processBuilder.start();
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream(), "gbk"));
            String line = in.readLine();
            if (tools.isInteger(line)) {
                int num = Integer.parseInt(line);
                String[] names = new String[num];
                Double[] up = new Double[num];
                for (int i = 0; i < num; i++) {
                    names[i] = in.readLine();
                }
                for (int i = 0; i < num; i++) {
                    up[i] = Double.parseDouble(in.readLine());
                }
                RankingPO rankingPO = new RankingPO(names, up);
                return rankingPO;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public void getMinuteData() {
//        int size = code_name.size();
//        String[] allCodes = new String[size];
//        int i = 0;
//        for (String key : code_name.keySet()) {
//            allCodes[i] = key;
//            i++;
//        }
//        int numOfThread = 1;
//        int m = size / numOfThread + 1;
//        for (int j = 0; j < numOfThread; j++) {
//            int start = 0;
//            String[] codes = new String[m];
//            for (int a = 0; a < m; a++) {
//                if (a + m * j > size - 1) {
//                    codes[a] = allCodes[size - 1];
//                } else {
//                    codes[a] = allCodes[a + m * j];
//                }
//            }
//            new MyThread(codes).start();
//        }
//    }

    public Hashtable<String, String> getCode_Name() {
        return code_name;
    }

    public Hashtable<String, String> getCode_Section() {
        return code_section;
    }

    public Hashtable<String, String> getCode_Industry() {
        return code_industry;
    }

    public Hashtable<String, String> getName_Code() {
        return name_code;
    }

    public Hashtable<String, NewsPO> getSingleNews() {
        return singleNews;
    }

    public void addSingleNews(String code, NewsPO newsPO) {
        singleNews.put(code, newsPO);
    }

    public Hashtable<String, MinuteDataPO> getMinuteDataPOHashtable() {
        return minuteDataPOHashtable;
    }

    public void setMinuteDataPOHashtable(String code, MinuteDataPO minuteDataPO) {
        JSONObject json = JSONObject.fromObject(minuteDataPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        System.out.println(str);
        if (minuteDataPOHashtable.containsKey(code)) {
            minuteDataPOHashtable.remove(code);
        }
//        System.out.print(code);
//        System.out.print(minuteDataPO);
        if (minuteDataPO != null) {
            minuteDataPOHashtable.put(code, minuteDataPO);
        }
    }

    private void setMinuteData() {
        Connection connection = connectionManager.getConnection("stock");
        for (String key : code_name.keySet()) {
            String sql = "select distinct minute,price,prediction,relativity from minute_data where code=" + key;
            PreparedStatement pstmt;
            ArrayList<String> minutes = new ArrayList<String>();
            ArrayList<Double> prices = new ArrayList<Double>();
            double prediction = 0;
            double relativity = 0;
            try {
                pstmt = (PreparedStatement) connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                boolean get = false;
                while (rs.next()) {
                    get = true;
                    minutes.add(rs.getString("minute"));
                    prices.add(Double.parseDouble(rs.getString("price")));
                    prediction = Double.parseDouble(rs.getString("prediction"));
                    relativity = Double.parseDouble(rs.getString("relativity"));
                }
                if (get) {
                    int size = minutes.size();
                    String[] minutes2 = (String[]) minutes.toArray(new String[size]);
                    Double[] prices2 = (Double[]) prices.toArray(new Double[size]);
                    double minimum = prices2[0];
                    for (int i = 0; i < size; i++) {
                        if (minimum > prices2[i]) {
                            minimum = prices2[i];
                        }
                    }
                    pool.freeConnection(connection);
                    MinuteDataPO minuteDataPO = new MinuteDataPO(minutes2, prices2, prediction, relativity, minimum);
                    this.setMinuteDataPOHashtable(key, minuteDataPO);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public MarketPO getYesterdayMarketPO() {
        return marketPO;
    }

    public RankingPO getRankingPO1() {
        return rankingPO1;
    }

    public RankingPO getRankingPO2() {
        return rankingPO2;
    }

    public RankingPO getRankingPO3() {
        return rankingPO3;
    }


//    public static void main(String[] args) {
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("HH");
//        String str = formatter.format(date);
//        System.out.print(str);
//        int hour = Integer.parseInt(str);
//        if (hour > 13) {
//            System.out.print("123456");
//        }
//        Cache cache = Cache.getInstance();
//        MarketPO marketPO=cache.getYesterdayMarketPO();
//        JSONObject json = JSONObject.fromObject(marketPO);//将java对象转换为json对象
//        String str = json.toString();//将json对象转换为字符串
//        System.out.print(str);
//    }
}
