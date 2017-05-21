package stocking.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
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
    private int size;

    public StockPO(String name, int code, Date start, Date over, double[] open, double[] high, double[] low,
                   double[] close, int[] volume, double[] adjClose, List<Date> dates, double[] average5,
                   double[] average10, double[] average20, double[] average30, double[] average60,
                   ArrayList<Double> profit, double variance, int size) {
        this.name = name;
        this.code = code;
        this.start = start;
        this.over = over;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.adjClose = adjClose;
        this.dates = dates;
        this.average5 = average5;
        this.average10 = average10;
        this.average20 = average20;
        this.average30 = average30;
        this.average60 = average60;
        this.profit = profit;
        this.variance = variance;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public Date getStart() {
        return start;
    }

    public Date getOver() {
        return over;
    }

    public double[] getOpen() {
        return open;
    }

    public double[] getHigh() {
        return high;
    }

    public double[] getLow() {
        return low;
    }

    public double[] getClose() {
        return close;
    }

    public int[] getVolume() {
        return volume;
    }

    public double[] getAdjClose() {
        return adjClose;
    }

    public List<Date> getDates() {
        return dates;
    }

    public double[] getAverage5() {
        return average5;
    }

    public double[] getAverage10() {
        return average10;
    }

    public double[] getAverage20() {
        return average20;
    }

    public double[] getAverage30() {
        return average30;
    }

    public double[] getAverage60() {
        return average60;
    }

    public ArrayList<Double> getProfit() {
        return profit;
    }

    public double getVariance() {
        return variance;
    }

    public int getSize() {
        return size;
    }
}
