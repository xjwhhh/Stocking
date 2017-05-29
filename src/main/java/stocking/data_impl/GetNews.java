package stocking.data_impl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xjwhhh on 2017/5/29.
 */
public class GetNews {
    public String getnews(){
        try {
            List<String> commands = new LinkedList<String>();
            commands.add("python");
            commands.add(getpath("src\\main\\java\\stocking\\python_Impl\\NewsSpider.py"));
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process pr = processBuilder.start();
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream(), "gbk"));
            String html;
            String title;
            //暂时组织成hashtable
            Hashtable<String,String> news=new Hashtable<String, String>();
            while ((html = in.readLine()) != null&&(title = in.readLine()) != null) {
                news.put(html,title);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
        GetNews getNews=new GetNews();
        getNews.getnews();
    }

    /**
     * 获取项目路径
     *
     * @param path
     * @return
     */
    private String getpath(String path) {
        String rpath = this.getClass().getResource("").getPath().substring(1);
        String[] pathlist = rpath.split("/");
        String newpath = "";
        for (int i = 0; i < pathlist.length - 4; i++) {
            newpath += (pathlist[i] + "\\");
        }
        newpath += path;
        return newpath;

    }
}
