package stocking.servlet;

import net.sf.json.JSONObject;
import stocking.data_impl.DataFactory_Data_Impl;
import stocking.data_service.GetNews_Data_Service;
import stocking.po.NewsPO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by dell on 2017/6/5.
 */
@WebServlet(name = "news")
public class News_Servlet extends HttpServlet {
    private GetNews_Data_Service gds;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new ToJSON().toJSONObject(request);
        if (jsonObject == null) {
            return;
        }
        System.out.print(jsonObject.toString());
        gds = DataFactory_Data_Impl.getInstance().getNews();
        String op = jsonObject.getString("op");//分为market和single
        if (op.equals("all")) {
            NewsPO result = gds.getMarketNews();
//            List<String> classify = new ArrayList<String>();
//            List<String> title = new ArrayList<String>();
//            List<String> time = new ArrayList<String>();
//            List<String> url = new ArrayList<String>();
//            for(int i = 0;i<10;i++){
//                classify.add("A");
//                title.add("B");
//                time.add("C");
//                url.add("D");
//            }
//            NewsPO result = new NewsPO(classify,title,time,url);
//            System.out.print(result.toString());
//            String[] classify = {"啊啊啊"};
//            String[] title = {"啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊"};
//            String[] time = {"啊啊"};
//            String[] url = {"https://www.baidu.com"};
//            NewsPO result = new NewsPO(classify,title,time,url);
//            response.setContentType("utf-8");
            response.setCharacterEncoding("utf-8");
            new SendByServlet().doSend(response, result);
        } else {
            String code = jsonObject.getString("code");
            NewsPO result = gds.getSingleNews(code);
            new SendByServlet().doSend(response, result);
        }
    }
}
