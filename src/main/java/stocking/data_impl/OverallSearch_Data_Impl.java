package stocking.data_impl;

import stocking.data_service.DataFactory_Data_Service;
import stocking.data_service.OverallSearch_Data_Service;
import stocking.po.MarketPO;
import stocking.po.StockPO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.text.*;

/**
 * Created by xjwhhh on 2017/5/23.
 */
public class OverallSearch_Data_Impl implements OverallSearch_Data_Service {
    Paths paths = new Paths();

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
        String todayStr = formatter.format(date);
        String yesterdayStr = formatter.format(new Date(date.getTime() - 24 * 60 * 60 * 1000));
        try {
            List<String> commands = new LinkedList<String>();
            commands.add("python");
            commands.add(paths.getProjectPath("src\\main\\java\\stocking\\python_Impl\\OverallSearch.py"));
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


    public static void main(String[] args) {
        OverallSearch_Data_Impl overallSearch_data_ = new OverallSearch_Data_Impl();
        overallSearch_data_.getMarketInfo(new Date());
    }

}
