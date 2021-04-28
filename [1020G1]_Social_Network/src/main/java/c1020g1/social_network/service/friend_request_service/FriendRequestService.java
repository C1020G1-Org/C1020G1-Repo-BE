package c1020g1.social_network.service.friend_request_service;

import c1020g1.social_network.model.FriendRequest;
import c1020g1.social_network.model.User;

import java.util.List;

public interface FriendRequestService {
    List<FriendRequest> findAllFriendRequest(Integer idUser);
    String saveFriendRequest(FriendRequest friendRequest);
    String deleteFriendRequest(Integer idFriendRequest);
}
