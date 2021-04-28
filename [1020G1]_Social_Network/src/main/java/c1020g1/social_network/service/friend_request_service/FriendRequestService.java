package c1020g1.social_network.service.friend_request_service;

import c1020g1.social_network.model.FriendRequest;

import java.util.List;

public interface FriendRequestService {
    List<FriendRequest> findAllFriendRequest(Integer idUser);

    String saveFriendRequest(FriendRequest friendRequest);

    String deleteFriendRequest(Integer idReceiverUser , Integer idSendUser);
}
