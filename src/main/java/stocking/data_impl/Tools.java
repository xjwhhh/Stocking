package stocking.data_impl;

import net.sf.json.JSONArray;
import stocking.po.StockWinnerSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class Tools {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Cache cache=Cache.getInstance();
    Hashtable<String,String> code_name=cache.getCode_Name();

    /**
     * 获取项目地址
     * @param path
     * @return
     */
    public String getProjectPath(String path) {
        String rpath = this.getClass().getResource("").getPath().substring(1);
        String[] pathList = rpath.split("/");
        String newPath = "";
        for (int i = 0; i < pathList.length - 4; i++) {
            newPath += (pathList[i] + "\\");
        }
        newPath += path;
        return newPath;
    }

    /**
     * 将json数组转化为String，中间以"/"分割
     *
     * @param jsonArray
     * @return
     */
    //TODO test
    public String jsonArrayToString(JSONArray jsonArray) {
        String arr = "";
        if(jsonArray.size()>0) {
            for (int i = 0; i < jsonArray.size() - 1; i++) {
                arr += (jsonArray.getString(i) + "/");
            }
            arr += (jsonArray.getString(jsonArray.size() - 1));
        }
        return arr;
    }

    public double[] getdouble(BufferedReader in, double[] doubles, int num) {
        String line = "";
        try {
            for (int i = 0; i < num; i++) {
                line = in.readLine();
                String[] t = line.split("\\s+");
                doubles[i] = Double.parseDouble(t[1]);
//                System.out.println(doubles[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doubles;
    }

    public int[] getint(BufferedReader in, int[] ints, int num) {
        String line = "";
        try {
            for (int i = 0; i < num; i++) {
                line = in.readLine();
                String[] t = line.split("\\s+");
                ints[i] = (int) Double.parseDouble(t[1]);
//                System.out.println(ints[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ints;
    }

    public Date[] getdate(BufferedReader in, Date[] dates, int num) {
        String line = "";
        try {
            for (int i = 0; i < num; i++) {
                line = in.readLine();
                String[] t = line.split("\\s+");
                dates[i] = formatter.parse(t[1]);
//                System.out.println(dates[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dates;
    }

    public List<StockWinnerSet> getStockWinnerSets(BufferedReader in, List<StockWinnerSet> stockWinnerSets, int num,int seriesNum) {
        for(int j=0;j<num;j++) {
            String line = "";
            List<String> codes = new ArrayList<String>();
            List<String> names = new ArrayList<String>();
            List<Double> profits = new ArrayList<Double>();
            try {
                for (int i = 0; i < seriesNum; i++) {
                    line = in.readLine();
                    String[] t = line.split("\\s+");
                    String code = t[0];
                    Double profit = Double.parseDouble(t[1]);
                    String name = code_name.get(code);
                    codes.add(code);
                    names.add(name);
                    profits.add(profit);
                }
                StockWinnerSet stockWinnerSet = new StockWinnerSet(codes, names, profits);
                stockWinnerSets.add(stockWinnerSet);
                in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return stockWinnerSets;
    }
}
