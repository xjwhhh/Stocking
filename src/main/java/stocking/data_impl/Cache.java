package stocking.data_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

/**
 * Created by xjwhhh on 2017/5/24.
 */
public class Cache {
    static private Cache cache;
    DBConnectionManager connectionManager = DBConnectionManager.getInstance();
    Hashtable pools = connectionManager.getPools();
    DBConnectionPool pool = (DBConnectionPool) pools.get("stock");

    private Hashtable<String, String> code_name = new Hashtable<String, String>();
    private Hashtable<String, String> name_code = new Hashtable<String, String>();

    private Cache() {
        this.setCode_Name();
        this.setName_Code();
        System.out.println(name_code.size());
    }

    static synchronized public Cache getInstance() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    private void setCode_Name() {
        Connection connection = connectionManager.getConnection("stock");
        String sql = "select name,code from basicinfo";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                code_name.put(rs.getString("code"), rs.getString("name"));
            }
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setName_Code() {
        Connection connection = connectionManager.getConnection("stock");
        String sql = "select name,code from basicinfo";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                name_code.put(rs.getString("name"), rs.getString("code"));
            }
            pool.freeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Hashtable<String, String> getCode_Name() {
        return code_name;
    }

    public Hashtable<String, String> getName_Code() {
        return name_code;
    }
}
