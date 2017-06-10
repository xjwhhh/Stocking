package stocking.po;

/**
 * 只用于历史查询，实时查询需要
 * Created by dell on 2017/5/21.
 */
public class StockPO {
    private String name;
    private String code;
    private String start;
    private String over;
    private double[] open;
    private double[] high;
    private double[] low;
    private int[] volume;//交易数
    private double[] adjClose;//复权后
    private String[] dates;
    private double[] average5;
    private double[] average10;
    private double[] average20;
    private double[] average30;
    private double[] average60;
    private double[] profit;//每日收益率
    private double variance;//相对方差
    private double highest;
    private double lowest;
    private double up;


    public StockPO(String name, String code, String start, String over, double[] open, double[] high, double[] low,
                   int[] volume, double[] adjClose, String[] dates, double[] average5, double[] average10,
                   double[] average20, double[] average30, double[] average60, double[] profit, double variance,
                   double highest, double lowest, double up) {
        this.name = name;
        this.code = code;
        this.start = start;
        this.over = over;
        this.open = open;
        this.high = high;
        this.low = low;
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
        this.highest = highest;
        this.lowest = lowest;
        this.up = up;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String date) {
        this.start = date;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String date) {
        this.over = date;
    }

    public double[] getOpen() {
        return open;
    }

    public void setOpen(double[] open) {
        this.open = open;
    }

    public double[] getHigh() {
        return high;
    }

    public void setHigh(double[] high) {
        this.high = high;
    }

    public double[] getLow() {
        return low;
    }

    public void setLow(double[] low) {
        this.low = low;
    }

    public int[] getVolume() {
        return volume;
    }

    public void setVolume(int[] volume) {
        this.volume = volume;
    }

    public double[] getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(double[] adjClose) {
        this.adjClose = adjClose;
    }

    public String[] getDates() {
        return dates;
    }

    public void setDates(String[] dates) {
        this.dates = dates;
    }

    public double[] getAverage5() {
        return average5;
    }

    public void setAverage5(double[] average5) {
        this.average5 = average5;
    }

    public double[] getAverage10() {
        return average10;
    }

    public void setAverage10(double[] average10) {
        this.average10 = average10;
    }

    public double[] getAverage20() {
        return average20;
    }

    public void setAverage20(double[] average20) {
        this.average20 = average20;
    }

    public double[] getAverage30() {
        return average30;
    }

    public void setAverage30(double[] average30) {
        this.average30 = average30;
    }

    public double[] getAverage60() {
        return average60;
    }

    public void setAverage60(double[] average60) {
        this.average60 = average60;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public double getHighest() {
        return highest;
    }

    public void setHighest(double highest) {
        this.highest = highest;
    }

    public double getLowest() {
        return lowest;
    }

    public void setLowest(double lowest) {
        this.lowest = lowest;
    }

    public double getUp() {
        return up;
    }

    public void setUp(double up) {
        this.up = up;
    }

}
