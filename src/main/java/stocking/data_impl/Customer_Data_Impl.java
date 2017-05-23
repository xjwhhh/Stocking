package stocking.data_impl;

import stocking.data_service.Customer_Data_Service;
import stocking.data_service.DataFactory_Data_Service;
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
import java.util.Hashtable;

/**
 * Created by dell on 2017/5/21.
 */
public class Customer_Data_Impl implements Customer_Data_Service{
    DBConnectionManager connectionManager=DBConnectionManager.getInstance();
    Hashtable pools=connectionManager.getPools();
    DBConnectionPool pool=(DBConnectionPool) pools.get("stock");


    public CustomerPO execute(String op, CustomerPO customerPO) {
        if(op.equals("login")){
            customerPO=this.login(customerPO);
        }
        else if(op.equals("signUp")){
            customerPO=this.signUp(customerPO);
        }
        else{
            customerPO=this.modify(customerPO);
        }
        return customerPO;
    }

    private CustomerPO login(CustomerPO customerPO) {
        DataFactory_Data_Service dataFactory_data_=DataFactory_Data_Impl.getInstance();
        Connection  connection=connectionManager.getConnection("stock");
        String id=customerPO.getId();
        String name=customerPO.getName();
        String password=customerPO.getPassword();
        String sql ="select id,decode(name,'key'),decode(password,'key') from clientinfo where id="+Integer.parseInt(id)+" and password=encode(?,'key')";
        PreparedStatement pstmt;
        try{
            pstmt= (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1,password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                id=(String.valueOf(rs.getInt("id")));
                name=(BlobtoString(rs.getBlob("decode(name,'key')")));
                password=(BlobtoString(rs.getBlob("decode(password,'key')")));
            }
            CustomerPO newCustomerPO=dataFactory_data_.getCustomerPO(id,name,password,"");
            pool.freeConnection(connection);
            return newCustomerPO;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private CustomerPO signUp(CustomerPO customerPO) {
        Connection  connection=connectionManager.getConnection("stock");
        String name=customerPO.getName();
        String password=customerPO.getPassword();
        String sql = "insert into clientinfo (id,name,password) values(NULL,encode(?,'key'),encode(?,'key'))";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1, customerPO.getName());
            pstmt.setString(2, customerPO.getPassword());
            pstmt.executeUpdate();
            pstmt = (PreparedStatement) connection.prepareStatement("select last_insert_id()");
            ResultSet re=pstmt.executeQuery();
            while(re.next()){
                customerPO.setId(String.valueOf(re.getInt(1)));
            }
            pstmt.close();
            pool.freeConnection(connection);
            return customerPO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private CustomerPO modify(CustomerPO customerPO ) {
        Connection  connection=connectionManager.getConnection("stock");
        String id=customerPO.getId();
        String name=customerPO.getName();
        String oldpassword=customerPO.getPassword();
        String newpassword=customerPO.getNewPassword();
        String sql = "update clientinfo set name=encode(?,'key'),password=encode(?,'key') where id="+Integer.parseInt(id)+" and password=encode(?,'key')";
        PreparedStatement pstmt;
        try{
            pstmt= (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,newpassword);
            pstmt.setString(3,oldpassword);
            pstmt.executeUpdate();
            pstmt.close();
            pool.freeConnection(connection);
            customerPO.setPassword(newpassword);
            return customerPO;
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

    public static void main(String [] args){

        CustomerPO customerPO=new CustomerPO("","xjw","456","");
        Customer_Data_Impl c=new Customer_Data_Impl();
        CustomerPO cc=c.signUp(customerPO);
//        CustomerPO newc=new CustomerPO();
//        newc.setName("456");
//        newc.setPassword("456");
//        c.modify(newc,customerPO);
//        System.out.print(cc.getName());
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
