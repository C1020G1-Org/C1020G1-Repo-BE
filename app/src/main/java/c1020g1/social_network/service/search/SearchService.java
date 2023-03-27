package c1020g1.social_network.service.search;

import c1020g1.social_network.model.Friends;
import c1020g1.social_network.model.GroupSocial;
import c1020g1.social_network.model.GroupUser;
import c1020g1.social_network.model.User;

import java.util.Date;
import java.util.List;

public interface SearchService {
    User findById(Integer id);

    List<User> findAllUserByNameContain(String name);

    List<User> advancedSearch(String name, Integer birthday, String gender, String province, String district, String ward,
                              String occupation, List<String> favorites);

    List<User> recommendation(Integer id, Date birthday, String gender, Integer province, List<String> favorites);

    List<String> getListFavourite(Integer id);

    List<GroupSocial> findAllGroupByName(String name);

    Friends findFriends(Integer userId, Integer friendId);

    GroupUser findGroupUser(Integer userId, Integer groupId);
}
