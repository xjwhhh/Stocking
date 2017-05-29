package stocking.data_impl;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import stocking.po.CustomerPO;

/**
 * Created by xjwhhh on 2017/5/25.
 */
public class MongoDB_Impl {
    MongoDBConnector mongoDBConnector=MongoDBConnector.getInstance();
    MongoDatabase mongoDatabase = mongoDBConnector.getMongoDatabase("mycol");
    MongoCollection<Document> collection=mongoDatabase.getCollection("clientStock");

    public static void main( String args[] ){
//        MongoDBConnector mongoDBConnector=MongoDBConnector.getInstance();
//        MongoDatabase mongoDatabase = mongoDBConnector.getMongoDatabase("mycol");
//        MongoCollection<Document> collection=mongoDatabase.getCollection("test");
//        try{
//            // 连接到 mongodb 服务
//            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
//
//            // 连接到数据库
//            MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
//            System.out.println("Connect to database successfully");
//
////	       //创建集合
////	        mongoDatabase.createCollection("test");
////	        System.out.println("集合创建成功");
//
//            //选择集合
//            MongoCollection<Document> collection=mongoDatabase.getCollection("test");
//            System.out.println("集合test选择成功");
//
//            //插入文档
//            /**
//             * 1. 创建文档 org.bson.Document 参数为key-value的格式
//             * 2. 创建文档集合List<Document>
//             * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
//             * */
//            Document document = new Document("title", "MongoDB").
//                    append("description", "database").
//                    append("likes", 100).
//                    append("by", "Fly");
//            List<Document> documents = new ArrayList<Document>();
//            documents.add(document);
//            collection.insertMany(documents);
//            System.out.println("文档插入成功");
//
//
//            //检索所有文档
//            /**
//             * 1. 获取迭代器FindIterable<Document>
//             * 2. 获取游标MongoCursor<Document>
//             * 3. 通过游标遍历检索出的文档集合
//             * */
//            FindIterable<Document> findIterable = collection.find();
//            MongoCursor<Document> mongoCursor = findIterable.iterator();
//            while(mongoCursor.hasNext()){
//                System.out.println(mongoCursor.next());
//            }
//
//        }catch(Exception e){
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//        }
//        String id=customerPO.getId();
//        BasicDBObject basicDBObject=new BasicDBObject();
//        basicDBObject.put("id",id);
//        BasicDBObject
//        DBObject dbObject=collection.findOneAndUpdate(basicDBObject,);
//        collection.updateMany(Filters.eq("title","MongoDB"),new Document("$set",new Document("title","123456")));

    }

    public void getClientStock(CustomerPO customerPO){
        String id=customerPO.getId();
        BasicDBObject basicDBObject=new BasicDBObject();
        basicDBObject.put("id",id);
        FindIterable<Document> findIterable=collection.find(basicDBObject);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while(mongoCursor.hasNext()){
            System.out.println(mongoCursor.next());
        }
    }

    public void setClientStock(CustomerPO customerPO){
        String id=customerPO.getId();
        collection.updateMany(Filters.eq("id",id),new Document("$set",new Document()));//新键值对
    }



}
