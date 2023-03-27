package c1020g1.social_network.service.friends_service;

import c1020g1.social_network.model.Friends;
import c1020g1.social_network.model.SuggestFriend;
import c1020g1.social_network.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface FriendsService {
    List<SuggestFriend> getAllSuggestFriend(Integer id);

    Friends findFriendsById(Integer id);

    void deleteFriends(Friends friends);


    Page<Friends> findAllFriendById(Integer idUser, Pageable pageable);

    List<Friends> findAllFriendByIdToCheck(Integer idUser);

    List<User> findMutualFriend(Integer receiveUser,Integer sendUser);

    String addNewFriend(Friends friends);
}
