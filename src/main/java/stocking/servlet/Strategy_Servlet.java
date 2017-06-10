package stocking.servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import stocking.data_impl.DataFactory_Data_Impl;
import stocking.data_service.Strategy_Data_Service;
import stocking.po.StockWinnerSet;
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
//    private Strategy_Data_Service sds;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new ToJSON().toJSONObject(request);
        if (jsonObject == null) {
            return;
        }
        System.out.print(request);

//        sds = DataFactory_Data_Impl.getInstance().strategy();
        String type = jsonObject.getString("type");//策略类型(M,A),动量策略传1，均值策略传2

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
        String isPla = jsonObject.getString("isPla");//是否为板块
        JSONArray stocks = JSONArray.fromObject(jsonObject.getString("stocks"));
        StrategyPO result;
        Date d1 = new Date();
        Date d2 = new Date();
        Date[] d = new Date[2];
        d[0] = d1;
        d[1] = d2;
        Double[] double1 = new Double[2];
        double1[0] = 1.1;
        double1[0] = 1.1;
        Double[] double2 = new Double[2];
        double2[0] = 1.1;
        double2[0] = 1.1;
        StockWinnerSet[] s = new StockWinnerSet[2];
//        result = sds.traceBack(type, start, end, form, hold, isPla, stocks);
        result = new StrategyPO(1.1,1.1,1.1,1.1,1.1,1.1,d,double1,double2,s);
        new SendByServlet().doSend(response, result);
    }
}
