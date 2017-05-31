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
    Paths paths = new Paths();

    public String getnews() {
        try {
            List<String> commands = new LinkedList<String>();
            commands.add("python");
            commands.add(paths.getProjectPath("src\\main\\java\\stocking\\python_Impl\\NewsSpider.py"));
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process pr = processBuilder.start();
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream(), "gbk"));
            String html;
            String title;
            //TODO 暂时组织成hashtable
            Hashtable<String, String> news = new Hashtable<String, String>();
            while ((html = in.readLine()) != null && (title = in.readLine()) != null) {
                news.put(html, title);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        GetNews getNews = new GetNews();
        getNews.getnews();
    }

}
