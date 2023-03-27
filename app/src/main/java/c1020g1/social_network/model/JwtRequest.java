package c1020g1.social_network.model;

import java.io.Serializable;


public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String accountName;
    private String password;

    public JwtRequest() {

    }

    public JwtRequest(String accountName, String password) {
        this.setAccountName(accountName);
        this.setPassword(password);
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
