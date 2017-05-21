package stocking.po;

/**
 * Created by dell on 2017/5/21.
 */
public class CustomerPO {
    private String name;
    private String password;

    public CustomerPO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
