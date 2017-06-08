package stocking.po;


/**
 * Created by dell on 2017/5/31.
 */
public class BGraphPO {
    private Double[] overProfit;
    private Double[] winChance;

    public BGraphPO(Double[] overProfit, Double[] winChance) {
        this.overProfit = overProfit;
        this.winChance = winChance;
    }

    public Double[] getOverProfit() {
        return overProfit;
    }

    public void setOverProfit(Double[] overProfit) {
        this.overProfit = overProfit;
    }

    public Double[] getWinChance() {
        return winChance;
    }

    public void setWinChance(Double[] winChance) {
        this.winChance = winChance;
    }
}
