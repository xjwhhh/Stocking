package stocking.data_impl;

import stocking.data_service.Minute_Data_Service;
import stocking.po.MinuteDataPO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xjwhhh on 2017/6/4.
 */
public class Minute_Data_Impl implements Minute_Data_Service {
    Tools tools = Tools.getInstance();
    Cache cache = Cache.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取股票分时数据
     *
     * @param identifier
     * @return
     */
    public MinuteDataPO getMinuteDataPO(String identifier) {
        String code = "";
        //判断是名字还是代码
        if (tools.isInteger(identifier)) {
            code = identifier;
        } else {
            Hashtable<String, String> name_code = cache.getName_Code();
            if (name_code.containsKey(identifier)) {
                code = cache.getName_Code().get(identifier);
            } else {
                return null;
            }
        }

        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String ofWeek = dateFm.format(date);

        //非周末，有实时数据
        if (!ofWeek.equals("星期六") && !ofWeek.equals("星期日")) {
            String dateStr = formatter.format(date);
            Date before = new Date(date.getTime() - 24 * 60 * 60 * 100 * 160);
            String beforeStr = formatter.format(before);
            try {
                List<String> commands = new LinkedList<String>();
                commands.add("python");
                commands.add(tools.getProjectPath("src\\main\\java\\stocking\\python_Impl\\MinuteData1.py"));
                commands.add(code);
                commands.add(dateStr);
                commands.add(beforeStr);
                ProcessBuilder processBuilder = new ProcessBuilder(commands);
                Process pr = processBuilder.start();
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(pr.getInputStream(), "gbk"));
                MinuteDataPO minuteDataPO = getMinuteDataPO(in);
                return minuteDataPO;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //周末，用星期五的数据
        else {
            while (!dateFm.format(date).equals("星期五")) {
                date = new Date(date.getTime() - 24 * 60 * 60 * 1000);
            }
            String dateStr = formatter.format(date);

            Date before = new Date(date.getTime() - 24 * 60 * 60 * 1000);
            for (int i = 0; i < 160; i++) {
                before = new Date(before.getTime() - 24 * 60 * 60 * 1000);
            }
            String beforeStr = formatter.format(before);
            try {
                List<String> commands = new LinkedList<String>();
                commands.add("python");
                commands.add(tools.getProjectPath("src\\main\\java\\stocking\\python_Impl\\MinuteData2.py"));
                commands.add(code);
                commands.add(dateStr);
                commands.add(beforeStr);
                ProcessBuilder processBuilder = new ProcessBuilder(commands);
                Process pr = processBuilder.start();
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(pr.getInputStream(), "gbk"));
                MinuteDataPO minuteDataPO = getMinuteDataPO(in);
                return minuteDataPO;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private MinuteDataPO getMinuteDataPO(BufferedReader in) {
        try {
            String line = in.readLine();
            double prediction = 0;
            double relativity = 0;
            if (tools.isInteger(line)) {
                int num = Integer.parseInt(line);
                String[] minute = new String[num];
                Double[] prices = new Double[num];
                for (int i = 0; i < num; i++) {
                    minute[i] = in.readLine();
                    prices[i] = Double.parseDouble(in.readLine());
                }
                prediction = Double.parseDouble(in.readLine());
                relativity = Double.parseDouble(in.readLine());
                MinuteDataPO minuteDataPO = new MinuteDataPO(minute, prices, prediction, relativity);
                return minuteDataPO;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
