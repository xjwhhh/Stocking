package stocking.po;

import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2017/5/30.
 */
public class StrategyPO {
    private double annualReturn;//年化收益率
    private double basicAnnualReturn;//基准年化收益率
    private double alpha;
    private double beta;
    private double sharpeRatio;
    private double maxDrawDown;//最大回撤
    private Date[] dates;
    private List<Double> profits;
    private List<Double> basicProfits;//基准收益
    private List<StockWinnerSet> sets;//获胜股票，mom为前百分之二十，avr为持股数

    public StrategyPO(double annualReturn, double basicAnnualReturn, double alpha, double beta,
                      double sharpeRatio, double maxDrawDown, Date[] dates, List<Double> profits,
                      List<Double> basicProfits, List<StockWinnerSet> sets) {
        this.annualReturn = annualReturn;
        this.basicAnnualReturn = basicAnnualReturn;
        this.alpha = alpha;
        this.beta = beta;
        this.sharpeRatio = sharpeRatio;
        this.maxDrawDown = maxDrawDown;
        this.dates = dates;
        this.profits = profits;
        this.basicProfits = basicProfits;
        this.sets = sets;
    }

    public double getAnnualReturn() {
        return annualReturn;
    }

    public void setAnnualReturn(double annualReturn) {
        this.annualReturn = annualReturn;
    }

    public double getBasicAnnualReturn() {
        return basicAnnualReturn;
    }

    public void setBasicAnnualReturn(double basicAnnualReturn) {
        this.basicAnnualReturn = basicAnnualReturn;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getSharpeRatio() {
        return sharpeRatio;
    }

    public void setSharpeRatio(double sharpeRatio) {
        this.sharpeRatio = sharpeRatio;
    }

    public double getMaxDrawDown() {
        return maxDrawDown;
    }

    public void setMaxDrawDown(double maxDrawDown) {
        this.maxDrawDown = maxDrawDown;
    }

    public Date[] getDates() {
        return dates;
    }

    public void setDates(Date[] dates) {
        this.dates = dates;
    }

    public List<Double> getProfits() {
        return profits;
    }

    public void setProfits(List<Double> profits) {
        this.profits = profits;
    }

    public List<Double> getBasicProfits() {
        return basicProfits;
    }

    public void setBasicProfits(List<Double> basicProfits) {
        this.basicProfits = basicProfits;
    }

    public List<StockWinnerSet> getSets() {
        return sets;
    }

    public void setSets(List<StockWinnerSet> sets) {
        this.sets = sets;
    }
}
