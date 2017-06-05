package stocking.servlet;

import net.sf.json.JSONObject;
import stocking.data_impl.DataFactory_Data_Impl;
import stocking.data_service.CustomerCollection_Data_Service;
import stocking.data_service.Customer_Data_Service;
import stocking.po.CollectionPO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dell on 2017/6/5.
 */
@WebServlet(name = "collection")
public class CustomerCollection_Servlet extends HttpServlet {
    private CustomerCollection_Data_Service cds;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new ToJSON().toJSONObject(request);
        if (jsonObject == null) {
            return;
        }

        cds = DataFactory_Data_Impl.getInstance().customerCollection();
        String op = jsonObject.getString("type");//三种操作get，add，delete
        String id = jsonObject.getString("id");


        if (op.equals("get")) {
            CollectionPO result = cds.getCollection(id);
            new Send().doSend(response, result);
        } else {
            String code = jsonObject.getString("code");
            boolean success = cds.execute(op, id, code);
            new Send().doSend(response, success);
        }
    }
}
