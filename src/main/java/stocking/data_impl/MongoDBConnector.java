package stocking.data_impl;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by xjwhhh on 2017/5/25.
 */
public class MongoDBConnector {
    static MongoDBConnector mongoDBConnector;
    static MongoClient mongoClient;

    /**
     * 初始化时连接到mongodb服务
     */
    private MongoDBConnector() {
        try {
            // 连接到 mongodb 服务
            mongoClient = new MongoClient("localhost", 27017);
            System.out.println("连接到mongo服务");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取唯一实例
     *
     * @return
     */
    static synchronized public MongoDBConnector getInstance() {
        if (mongoDBConnector == null) {
            mongoDBConnector = new MongoDBConnector();
        }
        return mongoDBConnector;
    }

    /**
     * 通过数据库名获取数据库连接
     *
     * @param databaseName
     * @return
     */
    public MongoDatabase getMongoDatabase(String databaseName) {
        try {
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
            System.out.println("Connect to mongodatabase successfully");
            return mongoDatabase;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Fail to connect to mongodatabase");
        }
        return null;
    }


}
