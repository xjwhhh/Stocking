package stocking.data_impl;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class Tools {
    static private Tools tool;

    private Tools() {
    }

    /**
     * 获取唯一实例
     *
     * @return
     */
    static synchronized public Tools getInstance() {
        if (tool == null) {
            tool = new Tools();
        }
        return tool;
    }

    /**
     * 判断字符串是否完全由数字构成
     *
     * @param str
     * @return
     */
    public boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 获取项目地址
     *
     * @param path
     * @return
     */
    public String getProjectPath(String path) {
//        String rpath = this.getClass().getResource("").getPath().substring(1);
//        String[] pathList = rpath.split("/");
//        String newPath = "";
//        for (int i = 0; i < pathList.length - 6; i++) {
//            newPath += (pathList[i] + "\\");
//        }
        String newPath = "C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\";
        newPath += path;
        System.out.print(newPath);
        return newPath;
    }

    /**
     * 将json数组转化为String，中间以"/"分割
     *
     * @param jsonArray
     * @return
     */
    public String jsonArrayToString(JSONArray jsonArray) {
        ArrayList<String> aarryList = new ArrayList<String>();
        String arr = "";
        if (jsonArray.size() > 0) {
            for (int i = 0; i < jsonArray.size() ; i++) {
                aarryList.add(jsonArray.getString(i));
//                System.out.println(jsonArray.getString(i));
            }
            aarryList.add(jsonArray.getString(jsonArray.size() - 1));
        }
        HashSet<String> hashSet = new HashSet<String>(aarryList);
//        System.out.print(hashSet.size());
        Iterator<String> it = hashSet.iterator();
        while (it.hasNext()) {
            arr += (it.next() + "/");
//            System.out.println(arr);
        }
        arr=arr.substring(0, arr.length() - 1);
        System.out.print(arr);
        return arr;
    }
    
}






