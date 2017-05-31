package stocking.data_impl;

import stocking.data_service.DataFactory_Data_Service;
import stocking.data_service.SingleSearch_Data_Service;
import stocking.po.StockPO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by xjwhhh on 2017/5/23.
 */
public class SingleSearch_Data_Impl implements SingleSearch_Data_Service {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Cache cache = Cache.getInstance();
    Hashtable<String, String> code_name = cache.getCode_Name();
    Hashtable<String, String> name_code = cache.getName_Code();
    Paths paths = new Paths();

    /**
     * 判断字符串是否完全由数字构成
     *
     * @param str
     * @return
     */
    private boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 通过股票代码获取股票板块
     *
     * @param code
     * @return
     */
    private String getsectionbycode(String code) {
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
        boolean isInteger = isInteger(identifier);
        String startDateStr = formatter.format(startDate);
        String endDateStr = formatter.format(endDate);
        String section = "";
        //判断是股票代码还是名字，如果是名字转换为代码
        if (isInteger) {
            //判断该股票代码是否存在
            if (code_name.contains(identifier)) {
                section = getsectionbycode(identifier);
            } else {
                return null;
            }
        } else {
            if (name_code.contains(identifier)) {
                identifier = name_code.get(identifier);
                section = getsectionbycode(identifier);
            } else {
                return null;
            }
        }
        if (section.length() > 0) {
            try {
                List<String> commands = new LinkedList<String>();
                commands.add("python");
                commands.add(paths.getProjectPath("src\\main\\java\\stocking\\python_Impl\\SingleSearch.py"));
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
                if (isInteger(line)) {
                    String name = name_code.get(identifier);
                    String code = identifier;
                    Date start = startDate;
                    Date over = endDate;
                    int num = Integer.parseInt(line);
                    double[] open = new double[num];
                    double[] high = new double[num];
                    double[] low = new double[num];
                    int[] volume = new int[num];
                    double[] adjClose = new double[num];
                    Date[] dates = new Date[num];
                    double[] average5 = new double[num];
                    double[] average10 = new double[num];
                    double[] average20 = new double[num];
                    double[] average30 = new double[num];
                    double[] average60 = new double[num];
                    double[] profit = new double[num];
                    double variance = 0;
                    open = getdouble(in, open, num);
                    in.readLine();
                    high = getdouble(in, high, num);
                    in.readLine();
                    low = getdouble(in, low, num);
                    in.readLine();
                    volume = getint(in, volume, num);
                    in.readLine();
                    adjClose = getdouble(in, adjClose, num);
                    in.readLine();
                    dates = getdate(in, dates, num);
                    in.readLine();
                    in.readLine();
                    average5 = getdouble(in, average5, num);
                    in.readLine();
                    in.readLine();
                    average10 = getdouble(in, average10, num);
                    in.readLine();
                    in.readLine();
                    average20 = getdouble(in, average20, num);
                    in.readLine();
                    in.readLine();
                    average30 = getdouble(in, average30, num);
                    in.readLine();
                    in.readLine();
                    average60 = getdouble(in, average60, num);
                    in.readLine();
                    profit = getdouble(in, profit, num);
                    in.readLine();
                    BigDecimal bd = new BigDecimal(in.readLine());
                    String varianceStr = bd.toPlainString();
                    variance = Double.parseDouble(varianceStr);
                    StockPO stockPO = new StockPO(name, code, start, over, open, high, low, volume, adjClose, dates, average5, average10, average20, average30, average60, profit, variance);
                    return stockPO;
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public double[] getdouble(BufferedReader in, double[] doubles, int num) {
        String line = "";
        try {
            for (int i = 0; i < num; i++) {
                line = in.readLine();
                String[] t = line.split("\\s+");
                doubles[i] = Double.parseDouble(t[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doubles;
    }

    public int[] getint(BufferedReader in, int[] ints, int num) {
        String line = "";
        try {
            for (int i = 0; i < num; i++) {
                line = in.readLine();
                String[] t = line.split("\\s+");
                ints[i] = (int) Double.parseDouble(t[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ints;
    }

    public Date[] getdate(BufferedReader in, Date[] dates, int num) {
        String line = "";
        try {
            for (int i = 0; i < num; i++) {
                line = in.readLine();
                String[] t = line.split("\\s+");
                dates[i] = formatter.parse(t[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dates;
    }


    public static void main(String[] args) {
        SingleSearch_Data_Impl singleSearch_data_ = new SingleSearch_Data_Impl();
        try {
            singleSearch_data_.getStockList("000001", new Date(), new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
