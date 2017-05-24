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
    DBConnectionManager connectionManager=DBConnectionManager.getInstance();
    Hashtable pools=connectionManager.getPools();
    DBConnectionPool pool=(DBConnectionPool) pools.get("stock");

    private Hashtable<String,String> code_name=getCode_Name();

    static synchronized public Cache getInstance() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    private Hashtable<String,String> getCode_Name(){
        Connection connection=connectionManager.getConnection("stock");
        Hashtable<String,String> code_name=new Hashtable<String, String>();
        String sql="select name,code from basicinfo";
        PreparedStatement pstmt;
        try{
            pstmt= (PreparedStatement) connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                code_name.put(rs.getString("code"),rs.getString("name"));
            }
            pool.freeConnection(connection);
            return code_name;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Hashtable<String,String> getCode_name(){
        return code_name;
    }
}
