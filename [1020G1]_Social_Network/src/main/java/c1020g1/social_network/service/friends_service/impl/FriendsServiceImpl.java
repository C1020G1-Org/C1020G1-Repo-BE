package c1020g1.social_network.service.friends_service.impl;

import c1020g1.social_network.model.Friends;
import c1020g1.social_network.model.User;
import c1020g1.social_network.repository.friends_repository.FriendsRepository;
import c1020g1.social_network.service.friends_service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    private FriendsRepository friendsRepository;

    @Override
    public List<Friends> findAllFriendById(Integer id) {
        return friendsRepository.findAllFriendById(id);
    }

    @Override
    public List<Object> getAllSuggestFriend(Integer id) {
        return friendsRepository.getAllSuggestFriend(id);
    }

    @Override
    public Friends findFriendsById(Integer id) {
        return friendsRepository.findFriendsById(id);
    }

    @Override
    public void deleteFriends(Friends friends) {
        friendsRepository.deleteFriendsByUserUserIdAndFriendUserId(friends.getUser().getUserId(),friends.getFriend().getUserId());
        friendsRepository.deleteFriendsByUserUserIdAndFriendUserId(friends.getFriend().getUserId(),friends.getUser().getUserId());
    }

    @Override
    public String addNewFriend(Friends friends) {
        //Check list friend of user has this friend or not.
        List<Friends> friendsList = friendsRepository.findAllFriendById(friends.getUser().getUserId());
        for (Friends friends1 : friendsList){
            if (friends1.getFriend().getUserId() == friends.getFriend().getUserId())
                return "NG";
        }
        friendsRepository.save(friends);
        return "OK";
    }

    @Override
    public List<User> findMutualFriend(Integer receiveUser, Integer sendUser) {
        return friendsRepository.findMutualFriend(receiveUser,sendUser);
    }
}
