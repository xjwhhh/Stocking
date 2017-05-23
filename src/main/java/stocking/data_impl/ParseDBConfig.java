package stocking.data_impl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;
import java.util.Iterator;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Created by xjwhhh on 2017/5/23.
 */
public class ParseDBConfig {
    /**
     * 构造函数
     */
    public ParseDBConfig() {
        // TODO Auto-generated constructor stub
    }
    /**
     * 读取xml配置文件
     * @param path
     * @return
     */
    public Vector readConfigInfo(String path)
    {
        String rpath=this.getClass().getResource("").getPath().substring(1);
        String[] pathlist=rpath.split("/");
        String newpath="";
        for(int i=0;i<pathlist.length-4;i++){
            newpath+=(pathlist[i]+"/");
        }
        newpath+="src/main/java/stocking/data_impl/";
        newpath+=path;
        Vector dsConfig=null;
        FileInputStream fi = null;
        try
        {
            fi=new FileInputStream(newpath);//读取路径文件
            dsConfig=new Vector();
            SAXBuilder sb=new SAXBuilder();
            Document doc=sb.build(fi);
            Element root=doc.getRootElement();
            List pools=root.getChildren();
            Element pool=null;
            Iterator allPool=pools.iterator();
            while(allPool.hasNext())
            {
                pool=(Element)allPool.next();
                DBConfigBean dscBean=new DBConfigBean();
                dscBean.setType(pool.getChild("type").getText());
                dscBean.setName(pool.getChild("name").getText());
                System.out.println(dscBean.getName());
                dscBean.setDriver(pool.getChild("driver").getText());
                dscBean.setUrl(pool.getChild("url").getText());
                dscBean.setUsername(pool.getChild("username").getText());
                dscBean.setPassword(pool.getChild("password").getText());
                dscBean.setMaxconn(Integer.parseInt(pool.getChild("maxconn").getText()));
                dsConfig.add(dscBean);
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        finally
        {
            try {
                fi.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return dsConfig;
    }

    public static void main(String[] args){

    }

}
