package stocking.servlet;

import net.sf.json.JSONObject;
import stocking.data_impl.DataFactory_Data_Impl;
import stocking.data_service.Minute_Data_Service;
import stocking.po.MinuteDataPO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2017/6/8.
 */
@WebServlet(name = "MinuteData_Servlet")
public class MinuteData_Servlet extends HttpServlet {
    private Minute_Data_Service mds;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new ToJSON().toJSONObject(request);
        if (jsonObject == null) {
            return;
        }

        mds = DataFactory_Data_Impl.getInstance().minute();
        String code = jsonObject.getString("code");

        MinuteDataPO po = mds.getMinuteDataPO(code);
        new SendByServlet().doSend(response, po);
    }
}
