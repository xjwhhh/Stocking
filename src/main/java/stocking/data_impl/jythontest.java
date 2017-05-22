package stocking.data_impl;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import java.util.*;

/**
 * Created by xjwhhh on 2017/5/22.
 */
public class jythontest {
    public static void main(String [] args){

        Properties props = new Properties();
        props.put("python.console.encoding", "UTF-8"); // Used to prevent: console: Failed to install '': java.nio.charset.UnsupportedCharsetException: cp0.
        props.put("python.security.respectJavaAccessibility", "false"); //don't respect java accessibility, so that we can access protected members on subclasses
        props.put("python.import.site","false");
        props.put("python.home","C:\\Users\\xjwhh\\AppData\\Roaming\\Python\\Python36\\site-packages");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        PythonInterpreter interp = new PythonInterpreter();
//        interp.exec("import sys");
//        interp.exec("sys.path.append('C:/Users/xjwhh/AppData/Roaming/Python/Python36')");//jython自己的
//        interp.exec("sys.path.append('C:/Users/xjwhh/AppData/Roaming/Python/Python36/site-packages')");//jython自己的
//        interp.exec("sys.path.append('F:/workspace/wxserver/WebContent/py')");//我们自己写的

//        System.out.print('2');
//        PythonInterpreter interpreter = new PythonInterpreter();
//        System.out.print(1);
        interp.execfile("C:/Users/xjwhh/PycharmProjects/untitled/stocking/mysqlmanage.py");
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.exec("days=('mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");
//        interpreter.exec("print days[1];");

    }
}
