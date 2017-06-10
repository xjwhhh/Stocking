package stocking.data_impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import stocking.data_impl.dbconnector.MongoDBConnector;
import stocking.data_service.CustomerCollection_Data_Service;
import stocking.po.CollectionPO;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by xjwhhh on 2017/6/5.
 */
public class CustomerCollection_Data_Impl implements CustomerCollection_Data_Service {
    MongoDBConnector mongoDBConnector = MongoDBConnector.getInstance();
    MongoDatabase mongoDatabase = mongoDBConnector.getMongoDatabase("mycol");
    MongoCollection<Document> collection = mongoDatabase.getCollection("stock");
    Cache cache = Cache.getInstance();
    Hashtable<String, String> code_name = cache.getCode_Name();

    /**
     * 获取用户收藏的股票
     *
     * @param id
     * @return
     */
    public CollectionPO getCollection(String id) {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("id", id);
        FindIterable<Document> findIterable = collection.find(basicDBObject);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        String[] codes = {};
        String[] names = {};
        while (mongoCursor.hasNext()) {
            codes = mongoCursor.next().get("stock").toString().split("/");
        }
        names = new String[codes.length];
        for (int i = 0; i < codes.length; i++) {
            if (code_name.containsKey(codes[i])) {
                names[i] = code_name.get(codes[i]);
            }
        }
        CollectionPO collectionPO = new CollectionPO(codes, names);
        return collectionPO;
    }

    /**
     * 根据op执行操作
     *
     * @param op
     * @param id
     * @param code
     * @return
     */
    public boolean execute(String op, String id, String code) {
        if (op.equals("add")) {
            return addStock(id, code);
        } else {
            return deleteStock(id, code);
        }
    }

    /**
     * 增加用户收藏的股票
     *
     * @param id
     * @param code
     * @return
     */
    private boolean addStock(String id, String code) {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("id", id);
        FindIterable<Document> findIterable = collection.find(basicDBObject);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        boolean x = false;
        while (mongoCursor.hasNext()) {
            x = true;
            String codes = mongoCursor.next().get("stock").toString();
            codes += ("/" + code);
            collection.updateMany(Filters.eq("id", id), new Document("$set", new Document("stock", codes)));
        }
        if (!x) {
            Document document = new Document("id", id).append("stock", code);
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
        }
        return true;
    }

    /**
     * 删除用户收藏的股票
     *
     * @param id
     * @param code
     * @return
     */
    private boolean deleteStock(String id, String code) {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("id", id);
        FindIterable<Document> findIterable = collection.find(basicDBObject);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        String[] codes = {};
        while (mongoCursor.hasNext()) {
            codes = mongoCursor.next().get("stock").toString().split("/");
        }
        ArrayList<String> newcodes = new ArrayList<String>();
        for (int i = 0; i < codes.length; i++) {
            if (!codes[i].equals(code)) {
                newcodes.add(codes[i]);
            }
        }
        String line = "";
        if (newcodes.size() > 0) {
            line += newcodes.get(0);
        }
        for (int i = 1; i < newcodes.size(); i++) {
            line += ("/" + newcodes.get(i));
        }
        collection.updateMany(Filters.eq("id", id), new Document("$set", new Document("stock", line)));
        return true;
    }
}
