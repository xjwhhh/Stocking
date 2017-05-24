package stocking.data_impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by xjwhhh on 2017/5/23.
 */
public class DBConnectionManager {
    static private DBConnectionManager instance;//唯一数据库连接池管理实例类
    static private int clients;                 //客户连接数
    private Vector drivers = new Vector();//驱动信息
    private Hashtable pools = new Hashtable();//连接池

    /**
     * 实例化管理类
     */
    public DBConnectionManager() {
        this.init();
    }

    /**
     * 新建五个连接
     */
    public void createInitConnection(){
        DBConnectionManager connectionManager = DBConnectionManager.getInstance();
        String name = "stock";//从上下文得到你要访问的数据库的名字
        Connection connection1 = connectionManager.getConnection(name);
        Connection connection2= connectionManager.getConnection(name);
        Connection connection3 = connectionManager.getConnection(name);
        Connection connection4 = connectionManager.getConnection(name);
        Connection connection5 = connectionManager.getConnection(name);
        DBConnectionPool pool=(DBConnectionPool) pools.get("stock");
//        System.out.print(pool.getInUsed());
//        System.out.print(pool.getfreeconnectionlength());
        pool.freeConnection(connection1);
        pool.freeConnection(connection2);
        pool.freeConnection(connection3);
        pool.freeConnection(connection4);
        pool.freeConnection(connection5);
//        System.out.print(pool.getInUsed());
//        System.out.print(pool.getfreeconnectionlength());
    }

    /**
     * 得到唯一实例管理类
     *
     * @return
     */
    static synchronized public DBConnectionManager getInstance() {
        if (instance == null) {
            instance = new DBConnectionManager();
        }
        return instance;
    }

    /**
     * 释放连接
     *
     * @param name
     * @param con
     */
    public void freeConnection(String name, Connection con) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);//根据关键名字得到连接池
        if (pool != null)
            pool.freeConnection(con);//释放连接
    }

    /**
     * 得到一个连接根据连接池的名字name
     *
     * @param name
     * @return
     */
    public Connection getConnection(String name) {
        DBConnectionPool pool = null;
        Connection con = null;
        pool = (DBConnectionPool) pools.get(name);//从名字中获取连接池
        con = pool.getConnection();//从选定的连接池中获得连接
        if (con != null)
            System.out.println("得到连接。。。");
        return con;
    }

    /**
     * 得到一个连接，根据连接池的名字和等待时间
     *
     * @param name
     * @param timeout
     * @return
     */
    public Connection getConnection(String name, long timeout) {
        DBConnectionPool pool = null;
        Connection con = null;
        pool = (DBConnectionPool) pools.get(name);//从名字中获取连接池
        con = pool.getConnection(timeout);//从选定的连接池中获得连接
        System.out.println("得到连接。。。");
        return con;
    }

    /**
     * 释放所有连接
     */
    public synchronized void release() {
        Enumeration allpools = pools.elements();
        while (allpools.hasMoreElements()) {
            DBConnectionPool pool = (DBConnectionPool) allpools.nextElement();
            if (pool != null) pool.release();
        }
        pools.clear();
    }

    /**
     * 创建连接池
     */
    private void createPools(DBConfigBean dsb) {
        DBConnectionPool dbpool = new DBConnectionPool();
        dbpool.setName(dsb.getName());
        dbpool.setDriver(dsb.getDriver());
        dbpool.setUrl(dsb.getUrl());
        dbpool.setUser(dsb.getUsername());
        dbpool.setPassword(dsb.getPassword());
        dbpool.setMaxConn(dsb.getMaxconn());
        System.out.println("ioio:" + dsb.getMaxconn());
        pools.put(dsb.getName(), dbpool);
    }

    /**
     * 初始化连接池的参数
     */
    private void init() {
        //加载驱动程序
        this.loadDrivers();
        //创建连接池
        Iterator alldriver = drivers.iterator();
        while (alldriver.hasNext()) {
            this.createPools((DBConfigBean) alldriver.next());
            System.out.println("创建连接池。。。");

        }
        System.out.println("创建连接池完毕。。。");

    }

    /**
     * 加载驱动程序
     */
    private void loadDrivers() {
        ParseDBConfig pd = new ParseDBConfig();
        //读取数据库配置文件
        drivers = pd.readConfigInfo("db.config.xml");
        System.out.println("加载驱动程序。。。");
    }

    public Hashtable getPools() {
        return pools;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DBConnectionManager db=DBConnectionManager.getInstance();
        db.createInitConnection();
//        String name = "stock";//从上下文得到你要访问的数据库的名字
//        Connection con = connectionManager.getConnection(name);

    }


}

