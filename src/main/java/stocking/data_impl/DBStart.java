package stocking.data_impl;

import stocking.data_impl.dbconnector.DBConnectionManager;
import stocking.data_impl.dbconnector.DBConnectionPool;
import stocking.data_service.DataFactory_Data_Service;

import java.util.Hashtable;

/**
 * Created by xjwhhh on 2017/6/4.
 */
public class DBStart {
    public static void main(String[] args){
        DataFactory_Data_Service dataFactory_data_service=DataFactory_Data_Impl.getInstance();
        DBConnectionManager connectionManager = DBConnectionManager.getInstance();
        Hashtable pools = connectionManager.getPools();
        DBConnectionPool pool = (DBConnectionPool) pools.get("stock");
        Tools tool=Tools.getInstance();
    }
}
