package stocking.data_impl;
//import org.python.core.PyFunction;
//import org.python.core.PyInteger;
//import org.python.core.PyObject;
//import org.python.util.PythonInterpreter;
//import java.util.*;
import java.io.*;
import java.util.List;
import java.util.LinkedList;

/**
 * Created by xjwhhh on 2017/5/22.
 */
public class jythontest {
    public static void main(String [] args){

//        Properties props = new Properties();
//        props.put("python.console.encoding", "UTF-8"); // Used to prevent: console: Failed to install '': java.nio.charset.UnsupportedCharsetException: cp0.
//        props.put("python.security.respectJavaAccessibility", "false"); //don't respect java accessibility, so that we can access protected members on subclasses
//        props.put("python.import.site","false");
//        props.put("python.home","C:\\Users\\xjwhh\\AppData\\Roaming\\Python\\Python36\\site-packages");
//        Properties preprops = System.getProperties();
//        PythonInterpreter.initialize(preprops, props, new String[0]);
//        PythonInterpreter interp = new PythonInterpreter();
//        interp.exec("import sys");
//        interp.exec("sys.path.append('C:/Users/xjwhh/AppData/Roaming/Python/Python36')");//jython自己的
//        interp.exec("sys.path.append('C:/Users/xjwhh/AppData/Roaming/Python/Python36/site-packages')");//jython自己的
//        interp.exec("sys.path.append('F:/workspace/wxserver/WebContent/py')");//我们自己写的

//        System.out.print('2');
//        PythonInterpreter interpreter = new PythonInterpreter();
//        System.out.print(1);
//        interp.execfile("C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\src\\main\\java\\stocking\\calculation\\test.py");
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.exec("C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\src\\main\\java\\stocking\\data_impl\\SingleSearch.py");
//        interpreter.exec("print days[1];");


//        try{
//            System.out.println("start");
//            Process pr = Runtime.getRuntime().exec("C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\src\\main\\java\\stocking\\data_impl\\SingleSearch.py");
//
//            BufferedReader in = new BufferedReader(new
//                    InputStreamReader(pr.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//            in.close();
//            pr.waitFor();
//            System.out.println("end");
//        } catch (Exception e){
//            e.printStackTrace();
//        }


try {
    List<String> commands=new LinkedList<String>();
    commands.add("python");
    commands.add("C:\\Users\\xjwhh\\IdeaProjects_Ultimate\\Stock_Analyzing_System\\src\\main\\java\\stocking\\calculation\\SingleSearch.py");
    commands.add("sza");
    commands.add("000001");
    commands.add("2017-05-12");
    commands.add("2017-05-19");
    ProcessBuilder processBuilder = new ProcessBuilder(commands);
    Process pr=processBuilder.start();
    BufferedReader in = new BufferedReader(new
            InputStreamReader(pr.getInputStream(),"gbk"));
    String line;
    while ((line = in.readLine()) != null) {
        System.out.println(line);
    }
    in.close();


}
catch (IOException e){
    e.printStackTrace();
}

    }

}

