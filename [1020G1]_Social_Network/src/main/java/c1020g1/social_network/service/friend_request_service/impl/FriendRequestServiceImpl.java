package c1020g1.social_network.service.friend_request_service.impl;

import c1020g1.social_network.model.FriendRequest;
import c1020g1.social_network.model.Friends;
import c1020g1.social_network.model.User;
import c1020g1.social_network.repository.friend_request_repository.FriendRequestRepository;
import c1020g1.social_network.service.friend_request_service.FriendRequestService;
import c1020g1.social_network.service.friends_service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FriendRequestServiceImpl implements FriendRequestService {

    @Autowired
    FriendRequestRepository friendRequestRepository;

    @Autowired
    FriendsService friendsService;

    @Override
    public List<FriendRequest> findAllFriendRequest(Integer idUser) {
        return friendRequestRepository.findAllFriendRequest(idUser);
    }

    @Override
    public String saveFriendRequest(FriendRequest newFriendRequest) {

        //Check list friend request of sendUser is has receiveUser or not.
        List<FriendRequest> sendUserFriendRequest =
                friendRequestRepository.findAllFriendRequest(newFriendRequest.getSendUser().getUserId());

        for (FriendRequest friendRequest : sendUserFriendRequest) {
            if (newFriendRequest.getReceiveUser().getUserId() == friendRequest.getSendUser().getUserId()) {
                return "NG";
            }
        }

        //Check list friend request of receiveUser is has sendUser or not.
        List<FriendRequest> receiverUserFriendRequest =
                friendRequestRepository.findAllFriendRequest(newFriendRequest.getReceiveUser().getUserId());
        for (FriendRequest friendRequest : receiverUserFriendRequest) {
            if (newFriendRequest.getSendUser().getUserId() == friendRequest.getSendUser().getUserId()) {
                return "NG";
            }
        }

        //Check two users was friends or not.
        List<Friends> sendUserFriends =
                friendsService.findAllFriendById(newFriendRequest.getSendUser().getUserId());
        for (Friends friends : sendUserFriends) {
            if (newFriendRequest.getReceiveUser().getUserId() == friends.getFriendsId()){
                return "NG";
            }
        }

        friendRequestRepository.save(newFriendRequest);
        return "OK";
    }

    @Override
    public String deleteFriendRequest(Integer idFriendRequest) {

        if(friendRequestRepository.findByFriendRequestId(idFriendRequest) != null){
            friendRequestRepository.deleteFriendRequestByFriendRequestId(idFriendRequest);
            return "OK";
        }
        return "NG";
    }
}
