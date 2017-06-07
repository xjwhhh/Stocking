package stocking.servlet;

import net.sf.json.JSONObject;
import stocking.data_impl.DataFactory_Data_Impl;
import stocking.data_service.Customer_Data_Service;
import stocking.po.CustomerPO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2017/5/21.
 */
@WebServlet(name = "customer")
public class Customer_Servlet extends HttpServlet {
    private Customer_Data_Service cds;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject jsonObject = new ToJSON().toJSONObject(request);
        if (jsonObject == null) {
            return;
        }

        cds = DataFactory_Data_Impl.getInstance().customer();
        String opType = jsonObject.getString("op"); //指明这是何种操作(login，signup，modify)
        String id = jsonObject.getString("id");   //登录时用id不用name，name设空
        String name = jsonObject.getString("name");
        String password = jsonObject.getString("password");
        String newPassword = jsonObject.getString("newPassword");
        CustomerPO customerPO = new CustomerPO(id, name, password, newPassword);
        CustomerPO result;

        result = cds.execute(opType, customerPO);

        new SendByServlet().doSend(response, result);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
