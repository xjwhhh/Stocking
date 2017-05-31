package stocking.data_impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class ToolsTest {

    @Test
    public void getProjectPath() throws Exception {
        Tools tools = new Tools();
        String subPath = "";
        String newPath = tools.getProjectPath(subPath);
        assertEquals("C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\", newPath);
    }

    @Test
    public void jsonArrayToString() throws Exception{

    }

}