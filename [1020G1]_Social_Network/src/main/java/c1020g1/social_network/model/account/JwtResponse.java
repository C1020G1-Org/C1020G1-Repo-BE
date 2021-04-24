package c1020g1.social_network.model.account;

import c1020g1.social_network.model.User;

import java.io.Serializable;


public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    private User user;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return this.jwtToken;
    }

}
