package c1020g1.social_network.service.friends_service;

import c1020g1.social_network.model.Friends;
import c1020g1.social_network.model.User;

import java.util.List;

public interface FriendsService {
    List<Object> getAllSuggestFriend(Integer id);

    Friends findFriendsById(Integer id);

    void deleteFriends(Friends friends);
    List<Friends> findAllFriendById(Integer idUser);
    List<User> findMutualFriend(Integer receiveUser,Integer sendUser);
    String addNewFriend(Friends friends);
}
