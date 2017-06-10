package stocking.po;

/**
 * Created by dell on 2017/5/30.
 */
public class StockWinnerSet {
    //以下三项一一对应
    private String[] codes;//编号
    private String[] names;//名字
    private Double[] profits;//收益率

    public StockWinnerSet(String[] codes, String[] names, Double[] profits) {
        this.codes = codes;
        this.names = names;
        this.profits = profits;
    }

    public String[] getCodes() {
        return codes;
    }

    public void setCodes(String[] codes) {
        this.codes = codes;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public Double[] getProfits() {
        return profits;
    }

    public void setProfits(Double[] profits) {
        this.profits = profits;
    }
}
