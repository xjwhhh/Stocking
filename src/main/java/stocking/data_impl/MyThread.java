package stocking.data_impl;

import stocking.data_service.DataFactory_Data_Service;
import stocking.data_service.Minute_Data_Service;
import stocking.po.MinuteDataPO;

import java.util.Hashtable;

/**
 * Created by xjwhhh on 2017/6/11.
 */
public class MyThread extends Thread {
    private String[] codes;

    public MyThread(String[] codes){
        this.codes=codes;
    }

    public void run(){

        DataFactory_Data_Service dataFactory_data_service=DataFactory_Data_Impl.getInstance();
        Minute_Data_Service minute_data_service=dataFactory_data_service.minute();
        for(String key:codes){
//            long startMili=System.currentTimeMillis();
            MinuteDataPO minuteDataPO=minute_data_service.getMinuteDataPO(key);
//            long endMili=System.currentTimeMillis();
//            System.out.print(key+"  ");
//            System.out.println(endMili-startMili);
        }
    }

    public static void main(String[] args){
        Cache cache=Cache.getInstance();
        Hashtable<String,String> code_name=cache.getCode_Name();
        int size=code_name.size();
        String [] allCodes=new String[size];
        int i=0;
        for(String key:code_name.keySet()){
            allCodes[i]=key;
            i++;
        }
        int numOfThread=10;
        int m=size/numOfThread+1;
        for (int j=0;j<numOfThread;j++){
            int start=0;
            String[] codes=new String[m];
            for(int a=0;a<m;a++){
                if(a+m*j>size-1){
                    codes[a]=allCodes[size-1];
                }
                else {
                    codes[a] = allCodes[a + m * j];
                }
            }
            new MyThread(codes).start();
        }

    }

}
