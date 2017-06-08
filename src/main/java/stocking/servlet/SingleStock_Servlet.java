package stocking.servlet;

import net.sf.json.JSONObject;
import stocking.data_impl.DataFactory_Data_Impl;
import stocking.data_service.SingleSearch_Data_Service;
import stocking.po.StockPO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by dell on 2017/5/23.
 */
@WebServlet(name = "singleStock")
public class SingleStock_Servlet extends HttpServlet {
    private SingleSearch_Data_Service ssds;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println();
        JSONObject jsonObject = new ToJSON().toJSONObject(request);

        if (jsonObject == null) {
            return;
        }
        System.out.print(jsonObject.toString());

        ssds = DataFactory_Data_Impl.getInstance().singleSearch();
        String identifier = jsonObject.getString("identifier");//code或name

        ParseDate pd = new ParseDate();
        Date start = null;
        Date end = null;
        try {
            start = pd.parse(jsonObject.getString("start"));
            end = pd.parse(jsonObject.getString("end"));
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        StockPO stockPO = null;
        try {
            stockPO = ssds.getStockList(identifier, start, end);
//            stockPO = new StockPO();
        } catch (ParseException e) {

            e.printStackTrace();
            return;
        }

        new SendByServlet().doSend(response, stockPO);
    }
}
