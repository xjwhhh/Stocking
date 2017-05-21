package stocking.data_service;

/**
 * Created by dell on 2017/5/21.
 */
public interface DataFactory_Data_Service {
    Customer_Data_Service customer();

    SingleSearch_Data_Service singleSearch();

    OverallSearch_Data_Service overall();
}
