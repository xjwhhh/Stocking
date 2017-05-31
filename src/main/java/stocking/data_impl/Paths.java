package stocking.data_impl;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class Paths {
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
}
