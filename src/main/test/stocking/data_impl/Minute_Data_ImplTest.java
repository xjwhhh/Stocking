package stocking.data_impl;

import javafx.stage.StageStyle;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import stocking.data_service.DataFactory_Data_Service;
import stocking.data_service.Minute_Data_Service;
import stocking.po.MinuteDataPO;

import static org.junit.Assert.assertEquals;

/**
 * Created by xjwhhh on 2017/6/8.
 */
public class Minute_Data_ImplTest {
    DataFactory_Data_Service dataFactory_data_service;
    Minute_Data_Service minute_data_service;

    @Before
    public void init() {
        dataFactory_data_service = DataFactory_Data_Impl.getInstance();
        minute_data_service = dataFactory_data_service.minute();
    }

    @Test
    public void getMinuteDataPO() throws Exception {
        MinuteDataPO minuteDataPO = minute_data_service.getMinuteDataPO("000001");
        JSONObject json = JSONObject.fromObject(minuteDataPO);//将java对象转换为json对象
        String str = json.toString();//将json对象转换为字符串
        String needStr = "{\"minute\":[\"15:00\",\"14:57\",\"14:56\",\"14:55\",\"14:54\",\"14:53\",\"14:52\",\"14:51\",\"14:50\",\"14:49\",\"14:48\",\"14:47\",\"14:46\",\"14:45\",\"14:44\",\"14:43\",\"14:42\",\"14:41\",\"14:40\",\"14:39\",\"14:38\",\"14:37\",\"14:36\",\"14:35\",\"14:34\",\"14:33\",\"14:32\",\"14:31\",\"14:30\",\"14:29\",\"14:28\",\"14:27\",\"14:26\",\"14:25\",\"14:24\",\"14:23\",\"14:22\",\"14:21\",\"14:20\",\"14:19\",\"14:18\",\"14:17\",\"14:16\",\"14:15\",\"14:14\",\"14:13\",\"14:12\",\"14:11\",\"14:10\",\"14:09\",\"14:08\",\"14:07\",\"14:06\",\"14:05\",\"14:04\",\"14:03\",\"14:02\",\"14:01\",\"14:00\",\"13:59\",\"13:58\",\"13:57\",\"13:56\",\"13:55\",\"13:54\",\"13:53\",\"13:52\",\"13:51\",\"13:50\",\"13:49\",\"13:48\",\"13:47\",\"13:46\",\"13:45\",\"13:44\",\"13:43\",\"13:42\",\"13:41\",\"13:40\",\"13:39\",\"13:38\",\"13:37\",\"13:36\",\"13:35\",\"13:34\",\"13:33\",\"13:32\",\"13:31\",\"13:30\",\"13:29\",\"13:28\",\"13:27\",\"13:26\",\"13:25\",\"13:24\",\"13:23\",\"13:22\",\"13:21\",\"13:20\",\"13:19\",\"13:18\",\"13:17\",\"13:16\",\"13:15\",\"13:14\",\"13:13\",\"13:12\",\"13:11\",\"13:10\",\"13:09\",\"13:08\",\"13:07\",\"13:06\",\"13:05\",\"13:04\",\"13:03\",\"13:02\",\"13:01\",\"13:00\",\"11:30\",\"11:29\",\"11:28\",\"11:27\",\"11:26\",\"11:25\",\"11:24\",\"11:23\",\"11:22\",\"11:21\",\"11:20\",\"11:19\",\"11:18\",\"11:17\",\"11:16\",\"11:15\",\"11:14\",\"11:13\",\"11:12\",\"11:11\",\"11:10\",\"11:09\",\"11:08\",\"11:07\",\"11:06\",\"11:05\",\"11:04\",\"11:03\",\"11:02\",\"11:01\",\"11:00\",\"10:59\",\"10:58\",\"10:57\",\"10:56\",\"10:55\",\"10:54\",\"10:53\",\"10:52\",\"10:51\",\"10:50\",\"10:49\",\"10:48\",\"10:47\",\"10:46\",\"10:45\",\"10:44\",\"10:43\",\"10:42\",\"10:41\",\"10:40\",\"10:39\",\"10:38\",\"10:37\",\"10:36\",\"10:35\",\"10:34\",\"10:33\",\"10:32\",\"10:31\",\"10:30\",\"10:29\",\"10:28\",\"10:27\",\"10:26\",\"10:25\",\"10:24\",\"10:23\",\"10:22\",\"10:21\",\"10:20\",\"10:19\",\"10:18\",\"10:17\",\"10:16\",\"10:15\",\"10:14\",\"10:13\",\"10:12\",\"10:11\",\"10:10\",\"10:09\",\"10:08\",\"10:07\",\"10:06\",\"10:05\",\"10:04\",\"10:03\",\"10:02\",\"10:01\",\"10:00\",\"09:59\",\"09:58\",\"09:57\",\"09:56\",\"09:55\",\"09:54\",\"09:53\",\"09:52\",\"09:51\",\"09:50\",\"09:49\",\"09:48\",\"09:47\",\"09:46\",\"09:45\",\"09:44\",\"09:43\",\"09:42\",\"09:41\",\"09:40\",\"09:39\",\"09:38\",\"09:37\",\"09:36\",\"09:35\",\"09:34\",\"09:33\",\"09:32\",\"09:31\",\"09:30\",\"09:25\"],\"prices\":[9.13,9.13,9.13,9.14,9.14,9.13,9.13,9.13,9.13,9.14,9.14,9.14,9.15,9.14,9.15,9.15,9.13,9.13,9.14,9.14,9.14,9.13,9.13,9.13,9.12,9.12,9.13,9.13,9.12,9.13,9.13,9.13,9.13,9.13,9.12,9.12,9.11,9.11,9.11,9.11,9.1,9.09,9.09,9.09,9.1,9.1,9.09,9.1,9.09,9.09,9.1,9.1,9.1,9.1,9.1,9.1,9.11,9.11,9.1,9.1,9.1,9.11,9.11,9.1,9.11,9.1,9.11,9.09,9.09,9.1,9.09,9.1,9.09,9.1,9.09,9.09,9.1,9.1,9.09,9.1,9.1,9.1,9.1,9.1,9.09,9.09,9.09,9.1,9.09,9.09,9.1,9.09,9.1,9.1,9.1,9.1,9.1,9.1,9.11,9.11,9.11,9.11,9.1,9.11,9.1,9.11,9.1,9.11,9.11,9.12,9.11,9.11,9.11,9.12,9.11,9.11,9.12,9.11,9.11,9.11,9.11,9.12,9.11,9.1,9.11,9.1,9.1,9.1,9.11,9.11,9.11,9.1,9.09,9.1,9.09,9.11,9.11,9.11,9.11,9.11,9.11,9.1,9.11,9.11,9.11,9.11,9.12,9.11,9.12,9.11,9.11,9.11,9.11,9.11,9.11,9.11,9.1,9.1,9.1,9.1,9.1,9.09,9.1,9.09,9.09,9.09,9.1,9.09,9.1,9.1,9.1,9.1,9.1,9.1,9.1,9.1,9.1,9.1,9.1,9.1,9.1,9.1,9.1,9.1,9.1,9.1,9.09,9.09,9.1,9.1,9.1,9.1,9.09,9.1,9.09,9.1,9.09,9.1,9.09,9.1,9.1,9.09,9.09,9.09,9.1,9.09,9.08,9.08,9.08,9.08,9.09,9.09,9.09,9.1,9.09,9.09,9.09,9.09,9.09,9.09,9.09,9.1,9.1,9.11,9.1,9.11,9.11,9.11,9.1,9.1,9.1,9.1,9.11,9.1,9.1,9.1,9.11,9.11,9.1,9.11,9.11]}";
        assertEquals(needStr, str);
    }

}