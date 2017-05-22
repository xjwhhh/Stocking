package stocking.data_impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Created by xjwhhh on 2017/5/22.
 */
public class MysqlConnector {
    public static Connection getConn(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/stock?useUnicode=true&characterEncoding=UTF-8&useSSL=false&zeroDateTimeBehavior=convertToNull";
        Connection conn = null;
        try{
            String username="root";
            String password="123456";
            Class.forName(driver);//classLoader,加载对应驱动
            conn = (Connection)DriverManager.getConnection(url,username,password);
            System.out.print("connect success");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    //TODO 连接池管理
}
