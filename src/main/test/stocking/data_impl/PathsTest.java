package stocking.data_impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xjwhhh on 2017/5/31.
 */
public class PathsTest {
    @Test
    public void getProjectPath() throws Exception {
        Paths paths = new Paths();
        String subpath = "";
        String newpath = paths.getProjectPath(subpath);
        assertEquals("C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\", newpath);
    }

}