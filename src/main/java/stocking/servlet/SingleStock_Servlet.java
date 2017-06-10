package stocking.servlet;

import net.sf.json.JSONObject;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import stocking.data_impl.DataFactory_Data_Impl;
import stocking.data_service.Minute_Data_Service;
import stocking.data_service.SingleSearch_Data_Service;
import stocking.po.MinuteDataPO;
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
    private Minute_Data_Service mds;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new ToJSON().toJSONObject(request);
        System.out.println(jsonObject.toString());
        if (jsonObject == null) {
            return;
        }

        ssds = DataFactory_Data_Impl.getInstance().singleSearch();
        String type = jsonObject.getString("type");//minute或normal
        String identifier = jsonObject.getString("identifier");//code或name

        if (type.equals("normal")) {
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
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }
            new SendByServlet().doSend(response, stockPO);
        } else {
            MinuteDataPO minutePO = mds.getMinuteDataPO(identifier);
            new SendByServlet().doSend(response, minutePO);
        }
    }
}
