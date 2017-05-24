package stocking.data_impl;

import stocking.data_service.DataFactory_Data_Service;
import stocking.data_service.OverallSearch_Data_Service;
import stocking.po.MarketPO;
import stocking.po.StockPO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.text.*;

/**
 * Created by xjwhhh on 2017/5/23.
 */
public class OverallSearch_Data_Impl implements OverallSearch_Data_Service {
    DBConnectionManager connectionManager=DBConnectionManager.getInstance();
    Hashtable pools=connectionManager.getPools();
    DBConnectionPool pool=(DBConnectionPool) pools.get("stock");
    DataFactory_Data_Service dataFactory_data_=DataFactory_Data_Impl.getInstance();
    Cache cache=Cache.getInstance();
    Hashtable<String,String> code_name=cache.getCode_name();

    public MarketPO getMarketInfo(Date date){
        Connection connection=connectionManager.getConnection("stock");
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
        String datestring = formatter.format(date);
        String formerdatestring=formatter.format(new Date(date.getTime()-24*60*60*1000));
        String[] tablename={"cyb","sha0","sha1","sha3","shb","sza","szb","zxb"};
        ArrayList<StockPO> stockPOArrayList=new ArrayList<StockPO>();
        for(int i=0;i<tablename.length;i++) {
            //TODO sql语句待定，不知道具体需要什么数据
            String sql = "select date,open,high,close,low,volume,amount,code from kdata_"+tablename[i]+ " where date=" + datestring;
            PreparedStatement pstmt;
            try {
                pstmt = (PreparedStatement) connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    StockPO stockPO = dataFactory_data_.getStockPO();
                    stockPO.setCode(rs.getString("code"));
                    stockPO.setName(code_name.get(stockPO.getCode()));
                    //TODO 剩余属性添加
                    stockPOArrayList.add(stockPO);
                }
                pool.freeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //TODO 对stockpolist处理产生marketpo
        return null;
    }



}
