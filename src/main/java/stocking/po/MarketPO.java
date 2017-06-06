package stocking.po;

/**
 * Created by dell on 2017/5/21.
 */
public class MarketPO {
    private double totalDeal;
    private int limitUpNum;
    private int limitDownNum;
    private int overFivePerNum;//涨幅超过5%的股票数
    private int belowFivePerNum;//跌幅超过5%的股票数
    private int oc_overPFivePerNum;//开盘-收盘大于5%*上一个交易日收盘价的股票个数
    private int oc_belowMFivePerNum;//开盘-收盘小于-5%*上一个交易日收盘价的股票个数
    private int numOfStock;

    public MarketPO(double totalDeal, int limitUpNum, int limitDownNum, int overFivePerNum,
                    int belowFivePerNum, int oc_overPFivePerNum, int oc_belowMFivePerNum, int numOfStock) {
        this.totalDeal = totalDeal;
        this.limitUpNum = limitUpNum;
        this.limitDownNum = limitDownNum;
        this.overFivePerNum = overFivePerNum;
        this.belowFivePerNum = belowFivePerNum;
        this.oc_overPFivePerNum = oc_overPFivePerNum;
        this.oc_belowMFivePerNum = oc_belowMFivePerNum;
        this.numOfStock = numOfStock;
    }

    public MarketPO() {
    }

    ;

    public double getTotalDeal() {
        return totalDeal;
    }

    public void setTotalDeal(double totalDeal) {
        this.totalDeal = totalDeal;
    }

    public int getLimitUpNum() {
        return limitUpNum;
    }

    public void setLimitUpNum(int limitUpNum) {
        this.limitUpNum = limitUpNum;
    }

    public int getLimitDownNum() {
        return limitDownNum;
    }

    public void setLimitDownNum(int limitDownNum) {
        this.limitDownNum = limitDownNum;
    }

    public int getOverFivePerNum() {
        return overFivePerNum;
    }

    public void setOverFivePerNum(int overFivePerNum) {
        this.overFivePerNum = overFivePerNum;
    }

    public int getBelowFivePerNum() {
        return belowFivePerNum;
    }

    public void setBelowFivePerNum(int belowFivePerNum) {
        this.belowFivePerNum = belowFivePerNum;
    }

    public int getOc_overPFivePerNum() {
        return oc_overPFivePerNum;
    }

    public void setOc_overPFivePerNum(int oc_overPFivePerNum) {
        this.oc_overPFivePerNum = oc_overPFivePerNum;
    }

    public int getOc_belowMFivePerNum() {
        return oc_belowMFivePerNum;
    }

    public void setOc_belowMFivePerNum(int oc_belowMFivePerNum) {
        this.oc_belowMFivePerNum = oc_belowMFivePerNum;
    }

    public int getNumOfStock() {
        return numOfStock;
    }

    public void setNumOfStock(int numOfStock) {
        this.numOfStock = numOfStock;
    }
}
