package stocking.data_impl;

import net.sf.json.JSONArray;
import stocking.po.StockWinnerSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class Tools {
    static private Tools tool;

    private Tools(){}

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
        String newPath="C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\";
        newPath += path;
        return newPath;
    }

    /**
     * 将json数组转化为String，中间以"/"分割
     *
     * @param jsonArray
     * @return
     */
    public String jsonArrayToString(JSONArray jsonArray) {
        String arr = "";
        if(jsonArray.size()>0) {
            for (int i = 0; i < jsonArray.size() - 1; i++) {
                arr += (jsonArray.getString(i) + "/");
            }
            arr += (jsonArray.getString(jsonArray.size() - 1));
        }
        return arr;
    }
}
