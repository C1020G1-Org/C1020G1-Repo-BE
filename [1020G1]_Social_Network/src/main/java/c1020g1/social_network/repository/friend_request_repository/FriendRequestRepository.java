package c1020g1.social_network.repository.friend_request_repository;

import c1020g1.social_network.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Integer> {

    @Query("select c from FriendRequest c where ?1 = c.receiveUser.userId")
    List<FriendRequest> findAllFriendRequest(Integer idUser);

    @Transactional
    @Modifying
    @Query("delete from FriendRequest c where c.receiveUser.userId = ?1 and c.sendUser.userId = ?2")
    void deleteFriendRequestByFriendRequestId(Integer idReceiverUser , Integer idSendUser);

    @Query("select c from FriendRequest c where c.receiveUser.userId = ?1 and c.sendUser.userId = ?2")
    FriendRequest findByReceiverUserIdAndSendUserId(Integer idReceiverUser , Integer idSendUser);
}
