package stocking.po;

import java.util.List;

/**
 * Created by dell on 2017/5/30.
 */
public class StockWinnerSet {
    //以下三项一一对应
    private List<String> codes;//编号
    private List<String> names;//名字
    private List<Double> profits;//收益率

    public StockWinnerSet(List<String> codes, List<String> names, List<Double> profits) {
        this.codes = codes;
        this.names = names;
        this.profits = profits;
    }

    public List<String> getCodes() {
        return codes;
    }

    public List<String> getNames() {
        return names;
    }

    public List<Double> getProfits() {
        return profits;
    }
}
