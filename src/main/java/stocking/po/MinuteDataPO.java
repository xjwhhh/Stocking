package stocking.po;

/**
 * Created by xjwhhh on 2017/6/8.
 */
public class MinuteDataPO {
    private String[] minute;
    private Double[] prices;
    private double prediction;
    private double relativity;

    public MinuteDataPO(String[] minute, Double[] prices, double prediction, double relativity) {
        this.minute = minute;
        this.prices = prices;
        this.prediction = prediction;
        this.relativity = relativity;
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

    public double getPrediction() {
        return prediction;
    }

    public void setPrediction(double prediction) {
        this.prediction = prediction;
    }

    public double getRelativity() {
        return relativity;
    }

    public void setRelativity(double relativity) {
        this.relativity = relativity;
    }

}
