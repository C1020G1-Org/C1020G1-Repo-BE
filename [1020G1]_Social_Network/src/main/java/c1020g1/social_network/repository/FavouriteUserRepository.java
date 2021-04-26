package c1020g1.social_network.repository;

import c1020g1.social_network.model.Favourite;
import c1020g1.social_network.model.FavouriteUser;
import c1020g1.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteUserRepository extends JpaRepository<FavouriteUser, Integer> {

    @Modifying
    @Query(value = "INSERT INTO favourite_user (favourite_id, user_id) VALUE (:#{#favourite.favouriteId}, :#{#user.userId})", nativeQuery = true)
    void createFavouriteUser(Favourite favourite, User user);
}
