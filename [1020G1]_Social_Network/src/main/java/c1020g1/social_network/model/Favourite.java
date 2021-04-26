package c1020g1.social_network.model;

import javax.persistence.*;

@Entity
@Table(name = "favourite")
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favourite_id")
    private Integer favouriteId;

    @Column(name = "favourite_name")
    private String favouriteName;

    public Integer getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(Integer favouriteId) {
        this.favouriteId = favouriteId;
    }

    public String getFavouriteName() {
        return favouriteName;
    }

    public void setFavouriteName(String favouriteName) {
        this.favouriteName = favouriteName;
    }
}
