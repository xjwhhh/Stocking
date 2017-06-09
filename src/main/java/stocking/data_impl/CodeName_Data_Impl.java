package stocking.data_impl;

import stocking.data_service.CodeName_Data_Service;
import stocking.po.StockInfoPO;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Created by xjwhhh on 2017/6/5.
 */
public class CodeName_Data_Impl implements CodeName_Data_Service {

    /**
     * 获取所有股票代码，名字
     *
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

    /**
     * 根据行业和板块获取股票代码，名字
     *
     * @param name
     * @return
     */
    @Override
    public StockInfoPO getPlate(String name) {
        String[] temp = name.split("/");
        String industry = temp[0];
        String section = temp[1];
        Cache cache = Cache.getInstance();
        Hashtable<String, String> code_industry = cache.getCode_Industry();
        Hashtable<String, String> code_section = cache.getCode_Section();
        Hashtable<String, String> code_name = cache.getCode_Name();
        ArrayList<String> code1 = new ArrayList<String>();
        ArrayList<String> code2 = new ArrayList<String>();
        if (!industry.equals("全部") && !section.equals("全部")) {
            for (String key : code_industry.keySet()) {
                if (code_industry.get(key).equals(industry)) {
                    code1.add(key);
                }
            }
            for (String key : code_section.keySet()) {
                if (code_section.get(key).equals(section)) {
                    code2.add(key);
                }
            }
            code1.retainAll(code2);
            ArrayList<String> names = new ArrayList<String>();
            for (int i = 0; i < code1.size(); i++) {
                names.add(code_name.get(code1.get(i)));
            }
            for (int i = 0; i < code1.size(); i++) {
            }
        } else if (industry.equals("全部") && !section.equals("全部")) {
            for (String key : code_section.keySet()) {
                if (code_section.get(key).equals(section)) {
                    code1.add(key);
                }
            }
        } else if (!industry.equals("全部") && section.equals("全部")) {
            for (String key : code_industry.keySet()) {
                if (code_industry.get(key).equals(industry)) {
                    code1.add(key);
                }
            }
        } else {
            for (String key : code_name.keySet()) {
                code1.add(key);
            }
        }
        String[] codes = new String[code1.size()];
        String[] names = new String[code1.size()];
        for (int i = 0; i < code1.size(); i++) {
            codes[i] = code1.get(i);
            names[i] = code_name.get(codes[i]);
        }
        StockInfoPO stockInfoPO = new StockInfoPO(codes, names);
        return stockInfoPO;
    }
}
