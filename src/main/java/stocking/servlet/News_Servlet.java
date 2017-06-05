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

        gds = DataFactory_Data_Impl.getInstance().getNews();
        String op = jsonObject.getString("op");//分为market和single

        if (op.equals("all")) {
            NewsPO result = gds.getMarketNews();
            new Send().doSend(response, result);
        } else {
            String code = jsonObject.getString("code");
            NewsPO result = gds.getSingleNews(code);
            new Send().doSend(response, result);
        }
    }
}
