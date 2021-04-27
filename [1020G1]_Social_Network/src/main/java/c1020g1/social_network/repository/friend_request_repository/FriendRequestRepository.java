package c1020g1.social_network.repository.friend_request_repository;

import c1020g1.social_network.model.FriendRequest;
import c1020g1.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest,Integer> {

    @Query("select c from FriendRequest c where ?1 = c.receiveUser.userId")
    List<FriendRequest> findAllFriendRequest(Integer idUser);

    void deleteFriendRequestByFriendRequestId(Integer idFriendRequest);

    @Query("select c from FriendRequest c where c.friendRequestId = ?1")
    FriendRequest findByFriendRequestId(Integer idFriendRequest);
}
