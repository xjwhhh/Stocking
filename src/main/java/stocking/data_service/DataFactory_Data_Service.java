package stocking.data_service;

/**
 * Created by dell on 2017/5/21.
 */
public interface DataFactory_Data_Service {
    Customer_Data_Service customer();

    SingleSearch_Data_Service singleSearch();

    OverallSearch_Data_Service overall();

    Strategy_Data_Service strategy();

    BGraph_Data_Service bGraph();

    CustomerCollection_Data_Service customerCollection();

    GetNews_Data_Service getNews();

    CodeName_Data_Service codeName();

    Minute_Data_Service minute();

    Ranking_Data_Service ranking();
}
