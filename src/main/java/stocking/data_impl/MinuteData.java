package stocking.data_impl;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/**
 * Created by xjwhhh on 2017/6/4.
 */
public class MinuteData {
    Tools tools=Tools.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public void getMinuteData(String code) {
        Date date=new Date();
        String dateStr=formatter.format(date);
        List<String> time=new ArrayList<String>();
        List<Double> price=new ArrayList<Double>();

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
//            int num=Integer.parseInt(in.readLine());
            System.out.print(in.readLine());
            int num=3;
            //num位3说明没数据
            if(num!=3) {
                for (int i = 0; i < num; i++) {
                    time.add(in.readLine());
                    System.out.println(time.get(i));
                    price.add(Double.parseDouble(in.readLine()));
                    System.out.println(price.get(i));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MinuteData minuteData=new MinuteData();
        minuteData.getMinuteData("000001");
    }
}
