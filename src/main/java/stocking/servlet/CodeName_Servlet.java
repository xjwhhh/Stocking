package stocking.servlet;

import stocking.data_impl.DataFactory_Data_Impl;
import stocking.data_service.CodeName_Data_Service;
import stocking.po.StockInfoPO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2017/6/5.
 */
@WebServlet(name = "codename")
public class CodeName_Servlet extends HttpServlet {
    private CodeName_Data_Service cds;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cds = DataFactory_Data_Impl.getInstance().codeName();
        StockInfoPO result = cds.get();
        new Send().doSend(response, result);
    }
}
