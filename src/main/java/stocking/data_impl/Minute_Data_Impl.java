package stocking.data_impl;

import net.sf.json.JSONObject;
import stocking.data_service.Minute_Data_Service;
import stocking.po.MinuteDataPO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

/**
 * Created by xjwhhh on 2017/6/4.
 */
public class Minute_Data_Impl implements Minute_Data_Service {
    Tools tools = Tools.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取股票分时数据
     * @param code
     * @return
     */
    public MinuteDataPO getMinuteDataPO (String code) {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String ofWeek = dateFm.format(date);
        //非周末，有实时数据
        if(!ofWeek.equals("星期六")&&!ofWeek.equals("星期日")) {
            String dateStr = formatter.format(date);
            Date before=new Date(date.getTime()-24*60*60*100*160);
            String beforeStr=formatter.format(before);
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
                String line = in.readLine();
                double prediction=0;
                double relativity=0;
                if (tools.isInteger(line)) {
                    int num = Integer.parseInt(line);
                        String[] minute = new String[num];
                        Double[] prices = new Double[num];
                        for (int i = 0; i < num; i++) {
                            minute[i] = in.readLine();
                            prices[i] = Double.parseDouble(in.readLine());
                    }
//                    prediction=Double.parseDouble(in.readLine());
//                    relativity=Double.parseDouble(in.readLine());
                    MinuteDataPO minuteDataPO = new MinuteDataPO(minute, prices,prediction,relativity);
                    return minuteDataPO;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //周末，用星期五的数据
        else{
            while(!dateFm.format(date).equals("星期五")) {
                date = new Date(date.getTime() - 24 * 60 * 60 * 1000);
            }
            String dateStr = formatter.format(date);
            System.out.println(dateStr);
//            }
//            System.out.print(date.toString());

            Date before=new Date(date.getTime()-24*60*60*1000);
            for(int i=0;i<160;i++){
                before=new Date(before.getTime()-24*60*60*1000);
            }
            String beforeStr=formatter.format(before);
            System.out.println(beforeStr);
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
//                int t=0;
//                while(t<100){
//                    System.out.println(in.readLine());
//                    t++;
//                }
                String line = in.readLine();
                double prediction=0;
                double relativity=0;
                if (tools.isInteger(line)) {
                    int num = Integer.parseInt(line);
                    String[] minute = new String[num];
                    Double[] prices = new Double[num];
                    for (int i = 0; i < num; i++) {
                        minute[i] = in.readLine();
                        prices[i] = Double.parseDouble(in.readLine());
                    }
//                    prediction=Double.parseDouble(in.readLine());
//                    relativity=Double.parseDouble(in.readLine());
                    MinuteDataPO minuteDataPO = new MinuteDataPO(minute, prices,prediction,relativity);
                    return minuteDataPO;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



//    public static void main(String[] args) {
//        Minute_Data_Impl minuteData = new Minute_Data_Impl();
//        MinuteDataPO minuteDataPO=minuteData.getMinuteDataPO("000001");
//        JSONObject json = JSONObject.fromObject(minuteDataPO);//将java对象转换为json对象
//        String str = json.toString();//将json对象转换为字符串
//        System.out.print(str);
//    }
}
