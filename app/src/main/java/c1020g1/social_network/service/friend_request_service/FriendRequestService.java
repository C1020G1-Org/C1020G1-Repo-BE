package c1020g1.social_network.service.friend_request_service;

import c1020g1.social_network.model.FriendRequest;
import c1020g1.social_network.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FriendRequestService {
    List<FriendRequest> findAllFriendRequest(Integer idUser);
    String saveFriendRequest(FriendRequest friendRequest);
    String deleteFriendRequest(Integer idReceiverUser, Integer idSendUser);
}
