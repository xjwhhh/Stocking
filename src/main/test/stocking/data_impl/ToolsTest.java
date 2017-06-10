package stocking.data_impl;

import net.sf.json.JSONArray;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
     * 纯非数字
     *
     * @throws Exception
     */
    @Test
    public void isInteger1() throws Exception {
        String s = "sss";
        boolean x = tools.isInteger(s);
        assertEquals(false, x);
    }

    /**
     * 数字与非数字混合
     *
     * @throws Exception
     */
    @Test
    public void isInteger2() throws Exception {
        String s = "sss00";
        boolean x = tools.isInteger(s);
        assertEquals(false, x);
    }

    /**
     * 纯数字
     *
     * @throws Exception
     */
    @Test
    public void isInteger3() throws Exception {
        String s = "000001";
        boolean x = tools.isInteger(s);
        assertEquals(true, x);
    }

    /**
     * 获取项目路径
     *
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
     *
     * @throws Exception
     */
    @Test
    public void jsonArrayToString() throws Exception {
        String[] array = new String[]{"JSON", "3", "test"};
        JSONArray jsonArray = JSONArray.fromObject(array);
        String str = tools.jsonArrayToString(jsonArray);
        String needStr = "JSON/3/test";
        assertEquals(needStr, str);
    }

}