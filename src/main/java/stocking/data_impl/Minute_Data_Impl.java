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
import java.text.*;

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

        if (cache.getMinuteDataPOHashtable().containsKey(code)) {
            System.out.print("cache");
            return cache.getMinuteDataPOHashtable().get(code);
        } else {

            Date date = new Date();
            SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
            String ofWeek = dateFm.format(date);

            //非周末，有实时数据
            if (!ofWeek.equals("星期六") && !ofWeek.equals("星期日")) {
                String dateStr = formatter.format(date);

                Date before = new Date(date.getTime() - 24 * 60 * 60 * 1000);
                for (int i = 0; i < 160; i++) {
                    before = new Date(before.getTime() - 24 * 60 * 60 * 1000);
                }
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
                    in.readLine();
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
    }

    private MinuteDataPO getMinuteDataPO(BufferedReader in) {
        try {
            String line = in.readLine();
            double prediction = 0;
            double relativity = 0;
            if (line!=null&&tools.isInteger(line) && Integer.parseInt(line) > 3) {
//                System.out.print(line);
                int num = Integer.parseInt(line);
                String[] minute = new String[num];
                Double[] prices = new Double[num];
                System.out.println(line);
                for (int i = 0; i < num; i++) {
                    minute[i] = in.readLine();
                    System.out.println(minute[i]);
                    prices[i] = Double.parseDouble(in.readLine());
                    System.out.println(prices[i]);
                }
                minute = (String[]) reverse(minute);
                prices = (Double[]) reverse(prices);
                if((line=in.readLine())!=null) {
                    prediction = Double.parseDouble(line);
                    DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.applyPattern("0.#");
                    String testdf = decimalFormat.format(prediction);
                    prediction = Double.parseDouble(testdf);
                }
                else{
                    prediction=0.5;
                }
                if((line=in.readLine())!=null) {
                    relativity = Double.parseDouble(line);
                    DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.applyPattern("0.#");
                    String testdf = decimalFormat.format(relativity);
                    relativity = Double.parseDouble(testdf);
                }
                else{
                    relativity = 0;
                }
//                System.out.println(in.readLine());
//                System.out.println(in.readLine());
                double minimum = prices[0];
                for (int i = 0; i < prices.length; i++) {
                    if (minimum > prices[i]) {
                        minimum = prices[i];
                    }
                }
                MinuteDataPO minuteDataPO = new MinuteDataPO(minute, prices, prediction, relativity, minimum);
                return minuteDataPO;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 数组反转
     *
     * @param objects
     * @return
     */
    private Object[] reverse(Object[] objects) {
        for (int i = 0; i < objects.length / 2; i++) {
            Object temp = objects[i];
            objects[i] = objects[objects.length - i - 1];
            objects[objects.length - i - 1] = temp;
        }
        return objects;
    }
}
