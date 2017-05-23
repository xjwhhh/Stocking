package stocking.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 只用于历史查询，实时查询需要
 * Created by dell on 2017/5/21.
 */
public class StockPO {
    private String name;
    private int code;
    private Date start;
    private Date over;
    private double[] open;
    private double[] high;
    private double[] low;
    private double[] close;
    private int[] volume;//交易数
    private double[] adjClose;//复权后
    private List<Date> dates;
    private double[] average5;
    private double[] average10;
    private double[] average20;
    private double[] average30;
    private double[] average60;
    private ArrayList<Double> profit;//每日收益率
    private double variance;//相对方差
}
