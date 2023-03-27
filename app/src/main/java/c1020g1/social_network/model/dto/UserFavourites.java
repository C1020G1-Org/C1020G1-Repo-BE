package c1020g1.social_network.model.dto;
import c1020g1.social_network.model.User;
import java.util.List;
public class UserFavourites {
    User user;
    List<String> favourites;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<String> getFavourites() {
        return favourites;
    }
    public void setFavourites(List<String> favourites) {
        this.favourites = favourites;
    }
}
