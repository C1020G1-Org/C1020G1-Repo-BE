package c1020g1.social_network.repository;

import c1020g1.social_network.model.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {
    @Query("select fv from Favourite fv " +
            "join FavouriteUser f on fv.favouriteId = f.favourite.favouriteId " +
            "join User u on f.user.userId = u.userId " +
            "where u.userId = :id")
    List<Favourite> getListFavourite(Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM favourite")
    Iterable<Favourite> findAllFavourite();
}
