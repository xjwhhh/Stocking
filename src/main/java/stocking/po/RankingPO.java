package stocking.po;

/**
 * Created by dell on 2017/6/9.
 */
public class RankingPO {
    private String[] names;
    private Double[] up;

    public RankingPO(String[] names, Double[] up) {
        this.names = names;
        this.up = up;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String[] getNames() {
        return names;
    }

    public void setUp(Double[] up) {
        this.up = up;
    }

    public Double[] getUp() {
        return up;
    }
}
