package c1020g1.social_network.model;

import javax.persistence.*;

@Entity
@Table(name = "favourite_user")
public class FavouriteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favourite_user_id")
    private Integer favouriteUserId;

    @ManyToOne
    @JoinColumn(name = "favourite_id",referencedColumnName = "favourite_id")
    Favourite favourite;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    User user;

    public Integer getFavouriteUserId() {
        return favouriteUserId;
    }

    public void setFavouriteUserId(Integer favouriteUserId) {
        this.favouriteUserId = favouriteUserId;
    }

    public Favourite getFavourite() {
        return favourite;
    }

    public void setFavourite(Favourite favourite) {
        this.favourite = favourite;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
