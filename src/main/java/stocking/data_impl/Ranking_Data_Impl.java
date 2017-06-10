package stocking.data_impl;

import net.sf.json.JSONObject;
import stocking.data_service.Ranking_Data_Service;
import stocking.po.NewsPO;
import stocking.po.RankingPO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

/**
 * Created by xjwhhh on 2017/6/9.
 */
public class Ranking_Data_Impl implements Ranking_Data_Service {
    Tools tools = Tools.getInstance();
    Cache cache=Cache.getInstance();

    public RankingPO getRanking(String reason) {
        if(reason.equals(" ")){
            return cache.getRankingPO1();
        }
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

    public static void main(String[] args) {
        Ranking_Data_Impl ranking_data = new Ranking_Data_Impl();
        ranking_data.getRanking("erty");

    }
}
