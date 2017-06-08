package stocking.data_impl;

import stocking.data_service.Minute_Data_Service;
import stocking.po.MinuteDataPO;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/**
 * Created by xjwhhh on 2017/6/4.
 */
public class Minute_Data_Impl implements Minute_Data_Service {
    Tools tools = Tools.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public MinuteDataPO getMinuteDataPO(String code) {
        Date date = new Date();
        String dateStr = formatter.format(date);

        try {
            List<String> commands = new LinkedList<String>();
            commands.add("python");
            commands.add(tools.getProjectPath("src\\main\\java\\stocking\\python_Impl\\MinuteData.py"));
            commands.add(code);
            commands.add(dateStr);
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process pr = processBuilder.start();
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream(), "gbk"));
            in.readLine();
            String line=in.readLine();
            if(tools.isInteger(line)) {
                int num = Integer.parseInt(line);
                //num为3说明没数据
                if (num != 3) {
                    String[] minute = new String[num];
                    Double[] prices = new Double[num];
                    for (int i = 0; i < num; i++) {
                        minute[i] = in.readLine();
                        prices[i] = Double.parseDouble(in.readLine());
                    }
                    MinuteDataPO minuteDataPO = new MinuteDataPO(minute, prices);
                    return minuteDataPO;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {
        Minute_Data_Impl minuteData = new Minute_Data_Impl();
        minuteData.getMinuteDataPO("000001");
    }
}
