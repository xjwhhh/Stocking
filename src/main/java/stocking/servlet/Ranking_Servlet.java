package stocking.servlet;

import stocking.data_impl.DataFactory_Data_Impl;
import stocking.data_service.Ranking_Data_Service;
import stocking.po.RankingPO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2017/6/9.
 */
@WebServlet(name = "Ranking_Servlet")
public class Ranking_Servlet extends HttpServlet {
    private Ranking_Data_Service rds;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        rds = DataFactory_Data_Impl.getInstance().ranking();
        RankingPO result = rds.getRanking();
        new SendByServlet().doSend(response, result);
    }
}
