package stocking.data_impl;

import stocking.data_impl.dbconnector.DBConnectionManager;
import stocking.data_impl.dbconnector.DBConnectionPool;
import stocking.po.CustomerPO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by xjwhhh on 2017/6/4.
 */
public class GetCodeByIndustry {
    DBConnectionManager connectionManager = DBConnectionManager.getInstance();
    Hashtable pools = connectionManager.getPools();
    DBConnectionPool pool = (DBConnectionPool) pools.get("stock");

    public void get(String industry) {
        Connection connection = connectionManager.getConnection("stock");
        List<String> codes=new ArrayList<String>();
        List<String> names=new ArrayList<String>();
        String sql = "select code,name from basicinfo where industry='" + industry + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                codes.add(rs.getString("code"));
                names.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

