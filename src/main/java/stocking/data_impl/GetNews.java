package stocking.data_impl;


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
public class GetNews {
    Tools tools = Tools.getInstance();

    /**
     * 获取股票相关新闻
     * @return
     */
    public NewsPO getMarketNews() {
        try {
            List<String> commands = new LinkedList<String>();
            commands.add("python");
            commands.add(tools.getProjectPath("src\\main\\java\\stocking\\python_Impl\\MarketNewsSpider.py"));
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process pr = processBuilder.start();
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream(), "gbk"));
            int num = Integer.parseInt(in.readLine());
            List<String> classify = new ArrayList<String>();
            List<String> title = new ArrayList<String>();
            List<String> time = new ArrayList<String>();
            List<String> url = new ArrayList<String>();
            for (int i = 0; i < num; i++) {
                classify.add(in.readLine());
            }
            for (int i = 0; i < num; i++) {
                title.add(in.readLine());
            }
            for (int i = 0; i < num; i++) {
                time.add(in.readLine());
            }
            for (int i = 0; i < num; i++) {
                url.add(in.readLine());
            }
            in.close();
            NewsPO newsPO = new NewsPO(classify, title, time, url);
            return newsPO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 信息地雷，获取单只股票相关新闻
     * @param code
     * @return
     */
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
            int num = Integer.parseInt(in.readLine());
            List<String> classify = new ArrayList<String>();
            List<String> title = new ArrayList<String>();
            List<String> time = new ArrayList<String>();
            List<String> url = new ArrayList<String>();
            for (int i = 0; i < num; i++) {
                classify.add(in.readLine());
            }
            for (int i = 0; i < num; i++) {
                title.add(in.readLine());
            }
            for (int i = 0; i < num; i++) {
                time.add(in.readLine());
            }
            for (int i = 0; i < num; i++) {
                url.add(in.readLine());
            }
            in.close();
            NewsPO newsPO = new NewsPO(classify, title, time, url);
            return newsPO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


//    public static void main(String[] args) {
//        GetNews getNews = new GetNews();
//        NewsPO newsPO=getNews.getMarketNews();
//        JSONObject json = JSONObject.fromObject(newsPO);//将java对象转换为json对象
//        String str = json.toString();//将json对象转换为字符串
//        System.out.print(str);
//
//        NewsPO newsPO1=getNews.getSingleNews("000001");
//        JSONObject json1 = JSONObject.fromObject(newsPO1);//将java对象转换为json对象
//        String str1 = json.toString();//将json对象转换为字符串
//        System.out.print(str1);
//    }

}
