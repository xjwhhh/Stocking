package stocking.data_impl;

import stocking.data_impl.dbconnector.DBConnectionManager;
import stocking.data_impl.dbconnector.DBConnectionPool;
import stocking.data_service.Customer_Data_Service;
import stocking.po.CustomerPO;

import java.sql.Connection;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

/**
 * Created by dell on 2017/5/21.
 */
public class Customer_Data_Impl implements Customer_Data_Service {
    DBConnectionManager connectionManager = DBConnectionManager.getInstance();
    Hashtable pools = connectionManager.getPools();
    DBConnectionPool pool = (DBConnectionPool) pools.get("stock");

    /**
     * 根据指令判断进行哪种操作
     *
     * @param op
     * @param customerPO
     * @return
     */
    public CustomerPO execute(String op, CustomerPO customerPO) {
        if (op.equals("login")) {
            customerPO = this.login(customerPO);
        } else if (op.equals("signUp")) {
            customerPO = this.signUp(customerPO);
        } else {
            customerPO = this.modify(customerPO);
        }
        return customerPO;
    }

    /**
     * 登录
     *
     * @param customerPO
     * @return
     */
    private CustomerPO login(CustomerPO customerPO) {
        Connection connection = connectionManager.getConnection("stock");
        String id = customerPO.getId();
        String name = customerPO.getName();
        String password = customerPO.getPassword();
        String sql = "select id,decode(name,'key'),decode(password,'key') from clientinfo where id=" + Integer.parseInt(id) + " and password=encode(?,'key')";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1, password);
            ResultSet rs = pstmt.executeQuery();
            boolean get = false;
            while (rs.next()) {
                get = true;
                id = (String.valueOf(rs.getInt("id")));
                name = (BlobToString(rs.getBlob("decode(name,'key')")));
                password = (BlobToString(rs.getBlob("decode(password,'key')")));
            }
            if (get) {
                CustomerPO newCustomerPO = new CustomerPO(id, name, password, "");
                pool.freeConnection(connection);
                return newCustomerPO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 注册
     *
     * @param customerPO
     * @return
     */
    private synchronized CustomerPO signUp(CustomerPO customerPO) {
        Connection connection = connectionManager.getConnection("stock");
        String name = customerPO.getName();
        String password = customerPO.getPassword();
        String sql = "insert into clientinfo (id,name,password) values(NULL,encode(?,'key'),encode(?,'key'))";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1, customerPO.getName());
            pstmt.setString(2, customerPO.getPassword());
            pstmt.executeUpdate();
            pstmt = (PreparedStatement) connection.prepareStatement("select last_insert_id()");
            ResultSet re = pstmt.executeQuery();
            while (re.next()) {
                customerPO.setId(String.valueOf(re.getInt(1)));
                customerPO.setNewPassword("");
            }
            pstmt.close();
            pool.freeConnection(connection);
            return customerPO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 更改用户信息
     *
     * @param customerPO
     * @return
     */
    private synchronized CustomerPO modify(CustomerPO customerPO) {
        Connection connection = connectionManager.getConnection("stock");
        String id = customerPO.getId();
        String name = customerPO.getName();
        String oldPassword = customerPO.getPassword();
        String newPassword = customerPO.getNewPassword();
        String sql = "update clientinfo set name=encode(?,'key'),password=encode(?,'key') where id=" + Integer.parseInt(id) + " and password=encode(?,'key')";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, newPassword);
            pstmt.setString(3, oldPassword);
            int rows=pstmt.executeUpdate();
            pstmt.close();
            pool.freeConnection(connection);
            if(rows>0) {
                customerPO.setPassword(newPassword);
                return customerPO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * blob数据类型转换为string
     *
     * @param blob
     * @return
     */
    private String BlobToString(Blob blob) {
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


}
