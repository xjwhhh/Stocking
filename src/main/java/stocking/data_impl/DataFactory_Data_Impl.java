package stocking.data_impl;

import stocking.data_service.*;

/**
 * Created by dell on 2017/5/21.
 */
public class DataFactory_Data_Impl implements DataFactory_Data_Service {
    private static DataFactory_Data_Service factoryDataService;

    private DataFactory_Data_Impl() {

    }

    public static DataFactory_Data_Service getInstance() {
        if (factoryDataService == null) {
            return new DataFactory_Data_Impl();
        } else {
            return factoryDataService;
        }
    }

    public Customer_Data_Service customer() {
        return new Customer_Data_Impl();
    }

    public SingleSearch_Data_Service singleSearch() {
        return new SingleSearch_Data_Impl();
    }

    public OverallSearch_Data_Service overall() {
        return new OverallSearch_Data_Impl();
    }

    public Strategy_Data_Service strategy() {
        return new Strategy_Data_Impl();
    }

    public BGraph_Data_Service bGraph() {
        return new BGraph_Data_Impl();
    }

    public CustomerCollection_Data_Service customerCollection() {
        return new CustomerCollection_Data_Impl();
    }

    @Override
    public GetNews_Data_Service getNews() {
        return new GetNews_Data_Impl();
    }

    @Override
    public CodeName_Data_Service codeName() {
        return new CodeName_Data_Impl();
    }

    @Override
    public Minute_Data_Service minute() {
        return new Minute_Data_Impl();
    }

    @Override
    public Ranking_Data_Service ranking() {
        return new Ranking_Data_Impl();
    }

}
