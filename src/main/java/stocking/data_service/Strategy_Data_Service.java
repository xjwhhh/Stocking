package stocking.data_service;

import net.sf.json.JSONArray;
import stocking.po.StrategyPO;

import java.util.Date;

/**
 * Created by dell on 2017/5/30.
 */
public interface Strategy_Data_Service {
    StrategyPO traceBack(String type, Date start, Date end, int form, int hold, JSONArray stocks);
}
