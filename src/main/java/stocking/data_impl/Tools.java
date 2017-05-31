package stocking.data_impl;

import net.sf.json.JSONArray;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class Tools {
    public String getProjectPath(String path) {
        String rpath = this.getClass().getResource("").getPath().substring(1);
        String[] pathList = rpath.split("/");
        String newPath = "";
        for (int i = 0; i < pathList.length - 4; i++) {
            newPath += (pathList[i] + "\\");
        }
        newPath += path;
        return newPath;
    }

    /**
     * 将json数组转化为String，中间以"/"分割
     *
     * @param jsonArray
     * @return
     */
    //TODO test
    public  static String jsonArrayToString(JSONArray jsonArray) {
        String arr = "";
        for (int i = 0; i < jsonArray.size() - 1; i++) {
            arr += (jsonArray.getString(i) + "/");
            System.out.println(arr);
        }
        arr += (jsonArray.getString(jsonArray.size() - 1));
        return arr;
    }
}
