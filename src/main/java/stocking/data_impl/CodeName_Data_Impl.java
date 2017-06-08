package stocking.data_impl;

import stocking.data_service.CodeName_Data_Service;
import stocking.po.StockInfoPO;

import java.util.Hashtable;
import java.util.Iterator;

/**
 * Created by xjwhhh on 2017/6/5.
 */
public class CodeName_Data_Impl implements CodeName_Data_Service {

    /**
     * 获取所有股票代码，名字
     * @return
     */
    public StockInfoPO get() {
        Cache cache = Cache.getInstance();
        Hashtable<String, String> code_name = cache.getCode_Name();
        String[] codes = new String[code_name.size()];
        String[] names = new String[code_name.size()];
        int i = 0;
        for (String key : code_name.keySet()) {
            codes[i] = key;
            names[i] = code_name.get(key);
            i++;
        }
        return new StockInfoPO(codes, names);
    }

    @Override
    public StockInfoPO getPlate(String name) {
        return null;
    }

}
