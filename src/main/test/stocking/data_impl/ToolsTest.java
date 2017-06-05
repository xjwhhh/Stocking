package stocking.data_impl;

import net.sf.json.JSONArray;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class ToolsTest {
    Tools tools;

    @Before
    public void init() {
        tools = Tools.getInstance();
    }

    /**
     * 获取项目路径
     * @throws Exception
     */
    @Test
    public void getProjectPath() throws Exception {
        String subPath = "";
        String newPath = tools.getProjectPath(subPath);
        assertEquals("C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\", newPath);
    }

    /**
     * JsonArray转String
     * @throws Exception
     */
    @Test
    public void jsonArrayToString() throws Exception {
        String[] array = new String[]{"JSON", "3", "test"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        String str=tools.jsonArrayToString(jsonArray);
        String needStr="JSON/3/test";
        assertEquals(needStr,str);
    }

}