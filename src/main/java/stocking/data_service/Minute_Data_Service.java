package stocking.data_service;

import stocking.po.MinuteDataPO;

/**
 * Created by xjwhhh on 2017/6/8.
 */
public interface Minute_Data_Service {
    MinuteDataPO getMinuteDataPO(String code);
}
