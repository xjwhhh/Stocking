package stocking.data_impl;

import stocking.data_service.Customer_Data_Service;
import stocking.po.CustomerPO;
import java.sql.Connection;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * Created by dell on 2017/5/21.
 */
public class Customer_Data_Impl implements Customer_Data_Service{

    public CustomerPO execute(String op, CustomerPO customerPO) {
        if(op.equals("login")){
            this.login(customerPO);
        }
        else if(op.equals("signUp")){
            this.signUp(customerPO);
        }
        else{
            //
        }
        return null;
    }

    private CustomerPO login(CustomerPO customerPO) {
        Connection connection=MysqlConnector.getConn();
        String id=customerPO.getId();
        String name=customerPO.getName();
        String password=customerPO.getPassword();
        CustomerPO newcustomerPO=new CustomerPO();
        String sql ="select id,decode(name,'key'),decode(password,'key') from clientinfo where id="+Integer.parseInt(id)+" and password=encode(?,'key')";
        PreparedStatement pstmt;
        try{
            pstmt= (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1,password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                newcustomerPO.setId(String.valueOf(rs.getInt("id")));
                newcustomerPO.setName(BlobtoString(rs.getBlob("decode(name,'key')")));
                newcustomerPO.setPassword(BlobtoString(rs.getBlob("decode(password,'key')")));
            }
            return newcustomerPO;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //考虑返回内容，返回一个成功或失败的标识就可以了吧？
    private CustomerPO signUp(CustomerPO customerPO) {
        Connection connection=MysqlConnector.getConn();
        String name=customerPO.getName();
        String password=customerPO.getPassword();
        String sql = "insert into clientinfo (id,name,password) values(NULL,encode(?,'key'),encode(?,'key'))";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1, customerPO.getName());
            pstmt.setString(2, customerPO.getPassword());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    更新什么内容呢，如果是密码，一个po是不够的
     */
    private CustomerPO modify(CustomerPO newcustomerPO,CustomerPO oldcustomerPO ) {
        Connection connection=MysqlConnector.getConn();
        String id=oldcustomerPO.getId();
        String oldpassword=oldcustomerPO.getPassword();
        String sql = "update clientinfo set name=encode(?,'key'),password=encode(?,'key') where id="+Integer.parseInt(id)+" and password=encode(?,'key')";
        PreparedStatement pstmt;
        try{
            pstmt= (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1,newcustomerPO.getName());
            pstmt.setString(2,newcustomerPO.getPassword());
            pstmt.setString(3,oldpassword);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public String BlobtoString(Blob blob) {
        try {
            InputStream is = blob.getBinaryStream();
            byte[] b = new byte[is.available()];
            is.read(b, 0, b.length);
            String str = new String(b);
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public CustomerPO login(String name, String password) {
        return new CustomerPO("wuyuhan","12345");
    }*/

    public static void main(String [] args){
        CustomerPO customerPO=new CustomerPO();
//        customerPO.setName("123");
        customerPO.setPassword("456");
        customerPO.setId("1");
        Customer_Data_Impl c=new Customer_Data_Impl();
        CustomerPO cc=c.login(customerPO);
//        CustomerPO newc=new CustomerPO();
//        newc.setName("456");
//        newc.setPassword("456");
//        c.modify(newc,customerPO);
        System.out.print(cc.getName());
//        Connection connection=MysqlConnector.getConn();
////        String name=customerPO.getName();
////        String password=customerPO.getPassword();
////        String sql = "insert into clientinfo(id,name,password)VALUES (NULL,customerPO.getName,)";
//        PreparedStatement pstmt;
//        try {
//            pstmt = (PreparedStatement) connection.prepareStatement(sql);
//            pstmt.execute();
//            System.out.print(12345);
////            ResultSet resultSet=pstmt.executeQuery();
////            while(resultSet.next()){
////                System.out.print(resultSet.getString(1));
////                System.out.print(resultSet.getString(2));
////                System.out.print(resultSet.getString(3));
////                System.out.print(resultSet.getString(4));
////            }
//
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
    }
}
