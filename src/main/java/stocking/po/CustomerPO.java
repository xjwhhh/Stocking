package stocking.po;

/**
 * Created by dell on 2017/5/21.
 */
public class CustomerPO {
    private String id;
    private String name;
    private String password;

    public CustomerPO(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
