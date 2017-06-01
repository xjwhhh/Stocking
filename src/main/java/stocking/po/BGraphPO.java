package stocking.po;

import java.util.List;

/**
 * Created by dell on 2017/5/31.
 */
public class BGraphPO {
    private List<Double> overProfit;
    private List<Double> winChance;

    public BGraphPO(List<Double> overProfit, List<Double> winChance) {
        this.overProfit = overProfit;
        this.winChance = winChance;
    }

    public List<Double> getOverProfit() {
        return overProfit;
    }

    public void setOverProfit(List<Double> overProfit) {
        this.overProfit = overProfit;
    }

    public List<Double> getWinChance() {
        return winChance;
    }

    public void setWinChance(List<Double> winChance) {
        this.winChance = winChance;
    }
}
