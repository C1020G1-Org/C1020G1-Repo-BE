package c1020g1.social_network.model.dto;

import c1020g1.social_network.annotation.AccountDuplicated;
import c1020g1.social_network.model.Favourite;
import c1020g1.social_network.model.User;
import c1020g1.social_network.model.Account;

import javax.validation.Valid;

public class UserCreateDTO {
    @Valid
    private User user;
    @Valid
    @AccountDuplicated
    private Account account;

    private Favourite[] favourites;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Favourite[] getFavourites() {
        return favourites;
    }

    public void setFavourites(Favourite[] favourites) {
        this.favourites = favourites;
    }
}
