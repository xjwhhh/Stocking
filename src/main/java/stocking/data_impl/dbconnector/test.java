package stocking.data_impl.dbconnector;


import stocking.data_impl.Cache;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * Created by xjwhhh on 2017/6/7.
 */
public class test {
    public static void main(String [] args) {
        String industry = "专业、科研服务业";
        String section = "沪市A股";
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
            System.out.println(code1.size());
            System.out.println(code2.size());
            code1.retainAll(code2);
            System.out.print(code1.size());
            ArrayList<String> names = new ArrayList<String>();
            for (int i = 0; i < code1.size(); i++) {
                names.add(code_name.get(code1.get(i)));
            }
            for (int i = 0; i < code1.size(); i++) {
                System.out.println(names.get(i) + code1.get(i));
            }
        }
        else if(industry.equals("全部")&&!section.equals("全部")){
            for (String key : code_section.keySet()) {
                if (code_section.get(key).equals(section)) {
                    code1.add(key);
                }
            }
        }
        else if(!industry.equals("全部")&&section.equals("全部")){
            for (String key : code_industry.keySet()) {
                if (code_industry.get(key).equals(industry)) {
                    code1.add(key);
                }
            }
        }
        else{
            for(String key:code_name.keySet()){
                code1.add(key);
            }
        }


    }
}
