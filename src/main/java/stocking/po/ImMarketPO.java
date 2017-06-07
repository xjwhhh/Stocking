package stocking.po;

/**
 * Created by dell on 2017/6/6.
 */
public class ImMarketPO {
    private String[] market;//板块名称
    private double[] volume;//成交额
    private double[] increase;//涨（跌）额
    private double[] rate;//涨（跌）幅

    public ImMarketPO(String[] market, double[] volume, double[] increase, double[] rate) {
        this.market = market;
        this.volume = volume;
        this.increase = increase;
        this.rate = rate;
    }

    public String[] getMarket() {
        return market;
    }

    public double[] getVolume() {
        return volume;
    }

    public double[] getIncrease() {
        return increase;
    }

    public double[] getRate() {
        return rate;
    }
}
