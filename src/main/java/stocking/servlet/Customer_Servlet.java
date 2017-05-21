package stocking.servlet;

import stocking.data_impl.Customer_Data_Impl;
import stocking.data_service.Customer_Data_Service;
import net.sf.json.JSONArray;
import stocking.po.CustomerPO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dell on 2017/5/21.
 */
public class Customer_Servlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
        /*
         *an example
         *should be implemented by using request
         */
        Customer_Data_Service cds = new Customer_Data_Impl();
        CustomerPO customerInfo = cds.login(null,null);
        JSONArray object = JSONArray.fromObject(customerInfo);

        PrintWriter pw=response.getWriter();
        pw.println(object.toString());
    }
}
