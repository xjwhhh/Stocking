package stocking.servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import stocking.data_impl.DataFactory_Data_Impl;
import stocking.data_service.Strategy_Data_Service;
import stocking.po.CustomerPO;
import stocking.po.StrategyPO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by dell on 2017/5/30.
 */
@WebServlet(name = "strategy")
public class Strategy_Servlet extends HttpServlet {
    private Strategy_Data_Service sds;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new ToJSON().toJSONObject(request);
        if (jsonObject == null) {
            return;
        }

        sds = DataFactory_Data_Impl.getInstance().strategy();
        String type = jsonObject.getString("type");//策略类型(M,A)

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
        int form = jsonObject.getInt("form");//形成期
        int hold = jsonObject.getInt("hold");//持有期
        JSONArray stocks = JSONArray.fromObject(jsonObject.getString("stocks"));
        StrategyPO result;

        result = sds.traceBack(type, start, end, form, hold, stocks);

        new Send().doSend(response, result);
    }
}
