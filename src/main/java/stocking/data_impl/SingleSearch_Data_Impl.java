package stocking.data_impl;

import stocking.data_service.DataFactory_Data_Service;
import stocking.data_service.SingleSearch_Data_Service;
import stocking.po.StockPO;

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

    DBConnectionManager connectionManager = DBConnectionManager.getInstance();
    Hashtable pools = connectionManager.getPools();
    DBConnectionPool pool = (DBConnectionPool) pools.get("stock");
    DataFactory_Data_Service dataFactory_data_ = DataFactory_Data_Impl.getInstance();
    Cache cache = Cache.getInstance();
    Hashtable<String, String> code_name = cache.getCode_Name();
    Hashtable<String, String> name_code = cache.getName_Code();

    public StockPO getStockList(String identifier, Date startDate, Date endDate) throws ParseException {
        boolean isInteger = isInteger(identifier);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = formatter.format(startDate);
        String endDateStr = formatter.format(endDate);
        Connection connection = connectionManager.getConnection("stock");
        String section = "";
        //判断是股票代码还是名字，如果是名字转换为代码
        if (isInteger) {
            section = getsectionbycode(identifier);
        } else {
            System.out.print(code_name.size());
            System.out.print(name_code.size());
            if (name_code.contains(identifier)) {
                section = getsectionbycode(name_code.get(identifier));
            } else {
                return null;
            }
        }
        if (section.length() > 0) {
            String sql = "select date,open,high,close,low,volume,code from kdata_" + section + " where code=" + identifier + " and date>'" + startDateStr + "' and date<'" + endDateStr + "' and autotype='前复权'";
            PreparedStatement pstmt;
            try {
                pstmt = (PreparedStatement) connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                int judge = 0;
                StockPO stockPO = dataFactory_data_.getStockPO();
                String name = code_name.get(identifier);
                String code = identifier;
                Date start = startDate;
                Date end = endDate;
                ArrayList<Date> dateArrayList = new ArrayList<Date>();
                ArrayList<Double> opens = new ArrayList<Double>();
                ArrayList<Double> highs = new ArrayList<Double>();
                ArrayList<Double> adjcloses = new ArrayList<Double>();
                ArrayList<Double> lows = new ArrayList<Double>();
                ArrayList<Integer> volumes = new ArrayList<Integer>();
                //TODO adjclose
                while (rs.next()) {
                    judge++;
                    dateArrayList.add(rs.getDate("date"));
                    opens.add(rs.getDouble("open"));
                    highs.add(rs.getDouble("high"));
                    adjcloses.add(rs.getDouble("close"));
                    lows.add(rs.getDouble("low"));
                    volumes.add((int) rs.getDouble("volume"));
//                    System.out.print(rs.getDate("date"));
                }
                //返回集是否为空
                if (judge > 0) {
                    stockPO.setName(name);
                    stockPO.setCode(code);
                    stockPO.setStart(start);
                    stockPO.setOver(end);
                    stockPO.setOpen(changeDoubleArrayList(opens));
                    stockPO.setHigh(changeDoubleArrayList(highs));
                    stockPO.setAdjClose(changeDoubleArrayList(adjcloses));
                    stockPO.setLow(changeDoubleArrayList(lows));
                    stockPO.setVolume(changeIntegerArrayList(volumes));
                    stockPO.setDates(changeDateArrayList(dateArrayList));
                    //TODO 其余属性，需要计算
                    return stockPO;
                }
                pool.freeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

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

    private double[] changeDoubleArrayList(ArrayList<Double> doubleArrayList) {
        int size = doubleArrayList.size();
        double[] doubles = new double[size];
        for (int i = 0; i < doubleArrayList.size(); i++) {
            doubles[i] = doubleArrayList.get(i);
        }
        return doubles;
    }

    private int[] changeIntegerArrayList(ArrayList<Integer> intArrayList) {
        int size = intArrayList.size();
        int[] ints = new int[size];
        for (int i = 0; i < intArrayList.size(); i++) {
            ints[i] = intArrayList.get(i);
        }
        return ints;
    }

    private Date[] changeDateArrayList(ArrayList<Date> dateArrayList) {
        int size = dateArrayList.size();
        Date[] dates = new Date[size];
        for (int i = 0; i < dateArrayList.size(); i++) {
            dates[i] = dateArrayList.get(i);
        }
        return dates;
    }

}
