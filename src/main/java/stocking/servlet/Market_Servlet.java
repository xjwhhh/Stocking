package stocking.servlet;

import net.sf.json.JSONObject;
import stocking.data_impl.DataFactory_Data_Impl;
import stocking.data_service.OverallSearch_Data_Service;
import stocking.po.MarketPO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by dell on 2017/5/24.
 */
@WebServlet(name = "market")
public class Market_Servlet extends HttpServlet {
    private OverallSearch_Data_Service ods;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new ToJSON().toJSONObject(request);
        System.out.println(jsonObject.toString());
        if (jsonObject == null) {
            return;
        }

        ods = DataFactory_Data_Impl.getInstance().overall();
        String dateStr = jsonObject.getString("date");
        System.out.print(dateStr);
        Date date = null;
        try {
            date = new ParseDate().parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        MarketPO marketPO = ods.getMarketInfo(date);
//        MarketPO marketPO = new MarketPO(50,50,50,50,50,50,50,50);
        new SendByServlet().doSend(response, marketPO);
    }
}
