package stocking.data_impl;


import stocking.data_service.GetNews_Data_Service;
import stocking.po.NewsPO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by xjwhhh on 2017/5/29.
 */
public class GetNews_Data_Impl implements GetNews_Data_Service {
    private Tools tools = Tools.getInstance();

    /**
     * 获取股票相关新闻
     *
     * @return
     */
    @Override
    public NewsPO getMarketNews() {
        try {
            List<String> commands = new LinkedList<String>();
            commands.add("python");
            commands.add(tools.getProjectPath("src\\main\\java\\stocking\\python_Impl\\MarketNewsSpider.py"));
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process pr = processBuilder.start();
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream(), "gbk"));
            String line = in.readLine();
            NewsPO newsPO = getNewsPO(in, line);
            return newsPO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 信息地雷，获取单只股票相关新闻
     *
     * @param code
     * @return
     */
    @Override
    public NewsPO getSingleNews(String code) {
        try {
            List<String> commands = new LinkedList<String>();
            commands.add("python");
            commands.add(tools.getProjectPath("src\\main\\java\\stocking\\python_Impl\\SingleNewsSpider.py"));
            commands.add(code);
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process pr = processBuilder.start();
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream(), "gbk"));
            String line = in.readLine();
            NewsPO newsPO = getNewsPO(in, line);
            return newsPO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private NewsPO getNewsPO(BufferedReader in, String line) {
        try {
            if (tools.isInteger(line)) {
                int num = Integer.parseInt(line);
                String[] classify = new String[num];
                String[] title = new String[num];
                String[] time = new String[num];
                String[] url = new String[num];
                for (int i = 0; i < num; i++) {
                    classify[i] = in.readLine();
                }
                for (int i = 0; i < num; i++) {
                    title[i] = in.readLine();
                }
                for (int i = 0; i < num; i++) {
                    time[i] = in.readLine();
                }
                for (int i = 0; i < num; i++) {
                    url[i] = in.readLine();
                }
                in.close();
                NewsPO newsPO = new NewsPO(classify, title, time, url);
                return newsPO;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
