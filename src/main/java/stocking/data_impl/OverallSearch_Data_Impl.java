package stocking.data_impl;

import stocking.data_service.OverallSearch_Data_Service;
import stocking.po.MarketPO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.text.*;

/**
 * Created by xjwhhh on 2017/5/23.
 */
public class OverallSearch_Data_Impl implements OverallSearch_Data_Service {
    Tools tools = new Tools();

    /**
     * 获取某日市场信息
     *
     * @param date
     * @return
     */
    public MarketPO getMarketInfo(Date date) {
        String[] data = new String[7];
        int i = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String ofWeek = dateFm.format(date);
        String todayStr = formatter.format(date);
        String yesterdayStr = "";
        if (ofWeek.equals("星期一")) {
            yesterdayStr = formatter.format(new Date(date.getTime() - 3 * 24 * 60 * 60 * 1000));
        } else {
            yesterdayStr = formatter.format(new Date(date.getTime() - 24 * 60 * 60 * 1000));
        }
        try {
            List<String> commands = new LinkedList<String>();
            commands.add("python");
            commands.add(tools.getProjectPath("src\\main\\java\\stocking\\python_Impl\\OverallSearch.py"));
            commands.add(todayStr);
            commands.add(yesterdayStr);
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process pr = processBuilder.start();
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream(), "gbk"));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
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
            MarketPO marketPO = new MarketPO(totalDeal, limitUpNum, limitDownNum, overFivePerNum, belowFivePerNum, oc_overPFivePerNum, oc_belowMFivePerNum);
            return marketPO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        OverallSearch_Data_Impl overallSearch_data_ = new OverallSearch_Data_Impl();
//        Date d=formatter.parse("2017-03-13");
//        MarketPO marketPO=overallSearch_data_.getMarketInfo(d);
//        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
//        String str = dateFm.format(new Date());
//        System.out.print(str);

    }

}
