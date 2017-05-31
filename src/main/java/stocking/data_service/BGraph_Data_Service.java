package stocking.data_service;

import net.sf.json.JSONArray;
import stocking.po.BGraphPO;

import java.util.Date;

/**
 * Created by dell on 2017/5/31.
 */
public interface BGraph_Data_Service {
    BGraphPO get(String type, Date start, Date end, String isHold, int interval, String isPla, JSONArray stocks);
}
