package stocking.data_impl;

import net.sf.json.JSONArray;
import stocking.data_service.BGraph_Data_Service;
import stocking.po.BGraphPO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class BGraph_Data_Impl implements BGraph_Data_Service {
    Tools tools = Tools.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取不同持有期/形成期下超额收益率与策略胜率
     *
     * @param type
     * @param start
     * @param end
     * @param isHold
     * @param interval
     * @param isPla
     * @param stocks
     * @return
     */
    public BGraphPO get(String type, Date start, Date end, String isHold, int interval, String isPla, JSONArray stocks) {
        String startDate = formatter.format(start);
        String endDate = formatter.format(end);
        try {
            List<String> commands = new LinkedList<String>();
            commands.add("python");
            commands.add(tools.getProjectPath("src\\main\\java\\stocking\\python_Impl\\BGraph.py"));

            String value = type + "?" + startDate + "?" + endDate + "?" + isHold + "?" + String.valueOf(interval) + "?" + isPla + "?" + tools.jsonArrayToString(stocks);
            commands.add(value);

            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process pr = processBuilder.start();
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream(), "gbk"));
            String line = in.readLine();
            System.out.print(line);
            if (line!=null&&tools.isInteger(line)) {
                int num = Integer.parseInt(line);
                Double[] profits = new Double[num];
                Double[] winChance = new Double[num];
                for (int i = 0; i < num; i++) {
                    profits[i] = Double.parseDouble(in.readLine());
                }
                for (int i = 0; i < num; i++) {
                    winChance[i] = Double.parseDouble(in.readLine());
                }
                BGraphPO bGraphPO = new BGraphPO(profits, winChance);
                in.close();
                return bGraphPO;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
