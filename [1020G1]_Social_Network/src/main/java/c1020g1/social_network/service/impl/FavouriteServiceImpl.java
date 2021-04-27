package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.Favourite;
import c1020g1.social_network.repository.FavouriteRepository;
import c1020g1.social_network.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    private FavouriteRepository favouriteRepository;

    /**
     * author: PhucPT
     * method: get iterable of all favourites
     * @return
     */
    @Override
    public Iterable<Favourite> getAllFavourite() {
        return favouriteRepository.findAllFavourite();
    }
}
