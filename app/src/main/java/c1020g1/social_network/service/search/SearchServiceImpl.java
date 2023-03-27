package c1020g1.social_network.service.search;
import c1020g1.social_network.model.*;
import c1020g1.social_network.repository.FavouriteRepository;
import c1020g1.social_network.repository.GroupRepository;
import c1020g1.social_network.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    SearchRepository searchRepository;

    @Autowired
    FavouriteRepository favouriteRepository;

    @Autowired
    GroupRepository groupRepository;

    @Override
    public User findById(Integer id) {
        return searchRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAllUserByNameContain(String name) {
        return searchRepository.findAllUserByNameContain(name);
    }

    @Override
    public List<User> advancedSearch(String name, Integer birthday, String gender, String province, String district, String ward,
                                     String occupation, List<String> favorites) {
        if (favorites.isEmpty()) {
            return searchRepository.advancedSearchNoFavourites(name, birthday, gender, province, district, ward, occupation);
        }
        return searchRepository.advancedSearch(name, birthday, gender, province, district, ward, occupation, favorites);
    }

    @Override
    public List<User> recommendation(Integer id, Date birthday, String gender, Integer province,
                                     List<String> favorites) {
        List<User> list;
        if (favorites.isEmpty()) {
            list = searchRepository.recommendationNoFavourite(id, birthday, gender, province);
            Collections.shuffle(list);
        } else {
            list = searchRepository.recommendation(id, birthday, gender, province, favorites);
            Collections.shuffle(list);
        }
        if (list.size() > 7) {
            list = list.subList(0, 7);
        }
        return list;
    }

    @Override
    public List<String> getListFavourite(Integer id) {
        List<String> list = new ArrayList<>();
        for (Favourite favourite : favouriteRepository.getListFavourite(id)) {
            list.add(favourite.getFavouriteName());
        }
        return list;
    }

    @Override
    public List<GroupSocial> findAllGroupByName(String name) {
        return groupRepository.findGroupByGroupName(name);
    }

    @Override
    public Friends findFriends(Integer userId, Integer friendId) {
        return searchRepository.findFriends(userId, friendId);
    }

    @Override
    public GroupUser findGroupUser(Integer userId, Integer groupId) {
        return searchRepository.findGroupUser(userId, groupId);
    }
}
