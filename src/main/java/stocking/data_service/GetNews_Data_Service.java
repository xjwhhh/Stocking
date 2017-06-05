package stocking.data_service;

import stocking.po.NewsPO;

/**
 * Created by dell on 2017/6/5.
 */
public interface GetNews_Data_Service {
    NewsPO getMarketNews();

    NewsPO getSingleNews(String code);
}
