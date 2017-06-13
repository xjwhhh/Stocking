package stocking.data_impl;

import stocking.data_service.SingleSearch_Data_Service;
import stocking.po.StockPO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xjwhhh on 2017/5/23.
 */
public class SingleSearch_Data_Impl implements SingleSearch_Data_Service {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Cache cache = Cache.getInstance();
    Hashtable<String, String> code_name = cache.getCode_Name();
    Hashtable<String, String> name_code = cache.getName_Code();
    Tools tools = Tools.getInstance();


    /**
     * 通过股票代码获取股票板块
     *
     * @param code
     * @return
     */
    private String getSectionByCode(String code) {
        String section = "";
        if (code.length() == 6) {
            String type = code.substring(0, 3);
            if (type.equals("600")) {
                section = "sha0";
            } else if (type.equals("601")) {
                section = "sha1";
            } else if (type.equals("603")) {
                section = "sha3";
            } else if (type.equals("900")) {
                section = "shb";
            } else if (type.equals("000")) {
                section = "sza";
            } else if (type.equals("200")) {
                section = "szb";
            } else if (type.equals("002")) {
                section = "zxb";
            } else if (type.equals("300")) {
                section = "cyb";
            }
        }
        return section;
    }

    /**
     * 获取股票一段时间内的个股信息
     *
     * @param identifier
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public StockPO getStockList(String identifier, Date startDate, Date endDate) throws ParseException {
        boolean isInteger = tools.isInteger(identifier);
        String startDateStr = formatter.format(startDate);
        String endDateStr = formatter.format(endDate);
        String section = "";
        //判断是股票代码还是名字，如果是名字转换为代码
        if (isInteger) {
            //判断该股票代码是否存在
            if (code_name.containsKey(identifier)) {
                section = getSectionByCode(identifier);
//                System.out.println("code");
            } else {
                return null;
            }
        } else {
            if (name_code.containsKey(identifier)) {
                identifier = name_code.get(identifier);
                section = getSectionByCode(identifier);
//                System.out.println("name");
            } else {
                return null;
            }
        }
        if (section.length() > 0) {
            try {
                List<String> commands = new LinkedList<String>();
                commands.add("python");
                commands.add(tools.getProjectPath("src\\main\\java\\stocking\\python_Impl\\SingleSearch.py"));
                commands.add(section);
                commands.add(identifier);
                commands.add(startDateStr);
                commands.add(endDateStr);
                ProcessBuilder processBuilder = new ProcessBuilder(commands);
                Process pr = processBuilder.start();
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(pr.getInputStream(), "gbk"));
                String line;
                line = in.readLine();//个数
//                System.out.println(line);
                if (line!=null&&tools.isInteger(line)&&Integer.parseInt(line)>0) {
                    String name = code_name.get(identifier);
                    String code = identifier;
                    String start = startDateStr;
                    String over = endDateStr;
                    int num = Integer.parseInt(line);
                    double[] open = new double[num];
                    double[] high = new double[num];
                    double[] low = new double[num];
                    int[] volume = new int[num];
                    double[] adjClose = new double[num];
                    String[] dates = new String[num];
                    double[] average5 = new double[num];
                    double[] average10 = new double[num];
                    double[] average20 = new double[num];
                    double[] average30 = new double[num];
                    double[] average60 = new double[num];
                    double[] profit = new double[num];
                    double variance = 0;
                    for (int i = 0; i < num; i++) {
                        open[i] = Double.parseDouble(in.readLine());
                    }
                    for (int i = 0; i < num; i++) {
                        high[i] = Double.parseDouble(in.readLine());
                    }
                    for (int i = 0; i < num; i++) {
                        low[i] = Double.parseDouble(in.readLine());
                    }
                    for (int i = 0; i < num; i++) {
                        volume[i] = (int) Double.parseDouble(in.readLine());
                    }
                    for (int i = 0; i < num; i++) {
                        adjClose[i] = Double.parseDouble(in.readLine());
                    }
                    for (int i = 0; i < num; i++) {
                        dates[i] = in.readLine();
                    }
                    for (int i = 0; i < num; i++) {
                        average5[i] = Double.parseDouble(in.readLine());
                    }
                    for (int i = 0; i < num; i++) {
                        average10[i] = Double.parseDouble(in.readLine());
                    }
                    for (int i = 0; i < num; i++) {
                        average20[i] = Double.parseDouble(in.readLine());
                    }
                    for (int i = 0; i < num; i++) {
                        average30[i] = Double.parseDouble(in.readLine());
                    }
                    for (int i = 0; i < num; i++) {
                        average60[i] = Double.parseDouble(in.readLine());
                    }
                    for (int i = 0; i < num; i++) {
                        profit[i] = Double.parseDouble(in.readLine());
                    }
                    BigDecimal bd = new BigDecimal(in.readLine());
                    String varianceStr = bd.toPlainString();
                    variance = Double.parseDouble(varianceStr);
                    double highest = high[0];
                    for (int i = 0; i < num; i++) {
                        if (highest < high[i]) {
                            highest = high[i];
                        }
                    }
                    double lowest = low[0];
                    for (int i = 0; i < num; i++) {
                        if (lowest > low[i]) {
                            lowest = low[i];
                        }
                    }
                    //保留五位小数
                    double up = (low[num - 1] - low[0]) / low[num - 1];
                    DecimalFormat decimalFormat=new DecimalFormat("#.00000");
                    up=Double.parseDouble(decimalFormat.format(up));
                    StockPO stockPO = new StockPO(name, code, start, over, open, high, low, volume, adjClose, dates, average5, average10, average20, average30, average60, profit, variance, highest, lowest, up);
                    return stockPO;
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


//    public static void main(String[] args) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        SingleSearch_Data_Impl singleSearch_data_ = new SingleSearch_Data_Impl();
//        try {
//            System.out.println("begin");
//            Date st=formatter.parse("2017-04-12");
//            Date end=formatter.parse("2017-04-20");
//            singleSearch_data_.getStockList("000001", st, end);
//            System.out.print("end");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }

}
