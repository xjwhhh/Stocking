package stocking.servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import stocking.data_impl.DataFactory_Data_Impl;
import stocking.data_service.BGraph_Data_Service;
import stocking.po.BGraphPO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by dell on 2017/5/31.
 */
@WebServlet(name = "BGraphServlet")
public class BGraphServlet extends HttpServlet {
    private BGraph_Data_Service bds;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new ToJSON().toJSONObject(request);
        if (jsonObject == null) {
            return;
        }

        bds = DataFactory_Data_Impl.getInstance().bGraph();
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
        String isHold = jsonObject.getString("isHold");//是否为形成期
        int interval = jsonObject.getInt("interval");//持有期
        String isPla = jsonObject.getString("isPla");
        JSONArray stocks = JSONArray.fromObject(jsonObject.getString("stocks"));
        BGraphPO result = bds.get(type, start, end, isHold, interval, isPla, stocks);

        new Send().doSend(response, result);
    }
}
