package stocking.po;

/**
 * Created by dell on 2017/5/21.
 */
public class CustomerPO extends Object{
    private String id;
    private String name;
    private String password;
    private String newPassword;//若非更改操作则为空

    public CustomerPO(String id, String name, String password, String newPassword) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.newPassword = newPassword;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public boolean equals(Object customerPO){
        if(customerPO instanceof CustomerPO) {
            return id.equals(((CustomerPO) customerPO).getId()) && name.equals(((CustomerPO) customerPO).getName()) && password.equals(((CustomerPO) customerPO).getPassword()) && newPassword.equals(((CustomerPO) customerPO).getNewPassword());
        }
        return false;
    }

}
