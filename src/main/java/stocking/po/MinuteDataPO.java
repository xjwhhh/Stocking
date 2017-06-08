package stocking.po;

/**
 * Created by xjwhhh on 2017/6/8.
 */
public class MinuteDataPO {
    private String[] minute;
    private Double[] prices;

    public MinuteDataPO(String[] minute, Double[] prices) {
        this.minute = minute;
        this.prices = prices;
    }

    public String[] getMinute() {
        return minute;
    }

    public void setMinute(String[] minute) {
        this.minute = minute;
    }

    public Double[] getPrices() {
        return prices;
    }

    public void setPrices(Double[] prices) {
        this.prices = prices;
    }

}
