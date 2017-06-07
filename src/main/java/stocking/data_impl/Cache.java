package stocking.data_impl;

import stocking.data_impl.dbconnector.DBConnectionManager;
import stocking.data_impl.dbconnector.DBConnectionPool;

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
    private Hashtable<String, String> code_section = new Hashtable<String, String>();
    private Hashtable<String, String> code_industry = new Hashtable<String, String>();
    private Hashtable<String, String> name_code = new Hashtable<String, String>();


    private Cache() {
        this.setStockInfo();
    }

    /**
     * 获取唯一实例
     *
     * @return
     */
    static synchronized public Cache getInstance() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    /**
     * 读取股票信息键值对
     */
    private void setStockInfo() {
        Connection connection = connectionManager.getConnection("stock");
        String sql = "select name,code,industry,section from basicinfo";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                code_name.put(rs.getString("code"), rs.getString("name"));
                code_industry.put(rs.getString("code"), rs.getString("industry"));
                code_section.put(rs.getString("code"), rs.getString("section"));
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

    public Hashtable<String, String> getCode_Section() {
        return code_section;
    }

    public Hashtable<String, String> getCode_Industry() {
        return code_industry;
    }

    public Hashtable<String, String> getName_Code() {
        return name_code;
    }
}
