package stocking.data_service;

import stocking.po.CollectionPO;

/**
 * Created by dell on 2017/6/5.
 */
public interface CustomerCollection_Data_Service {
    CollectionPO getCollection(String id);

    CollectionPO execute(String op, String id, String code);//op分为add，delete两类，code为股票编码
}
