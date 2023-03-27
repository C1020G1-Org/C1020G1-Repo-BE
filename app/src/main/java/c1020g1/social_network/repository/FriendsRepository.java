package c1020g1.social_network.repository;

import c1020g1.social_network.model.Friends;
import c1020g1.social_network.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Integer> {
    @Query("select c from Friends c where c.user.userId = ?1")
    Page<Friends> findAllFriendById(Integer userId, Pageable pageable);


    // Câu lấy ra user_id được gợi ý với user login
    @Query(value = "select tmp_2.user_id," +
                    " count(tmp_2.user_id) as number_of_mutual_friend" +
            " from (" +
            " select f.friend_id" +
            " from friends f" +
            " where f.user_id = ?1) as tmp_1" +
            ", (" +
                " select friend_id, user_id" +
                " from friends" +
                " where user_id in (" +
                    " select friend_id" +
                    " from friends" +
                    " where user_id in(" +
                        " select friend_id" +
                        " from friends f" +
                        " where f.user_id = ?1)" +
                    " and friend_id not in (" +
                    " select f.friend_id" +
                    " from friends f" +
                    " where f.user_id = ?1))) as tmp_2" +
                " where (tmp_1.friend_id = tmp_2.friend_id) and (tmp_2.user_id != ?1)" +
            " group by tmp_2.user_id" +
            " order by number_of_mutual_friend desc", nativeQuery = true)

    List<Integer> getAllSuggestFriend(Integer userId);


    @Query("select f from Friends f where f.friendsId = ?1")
    Friends findFriendsById(Integer friendsId);

    void deleteFriendsByUserUserIdAndFriendUserId(Integer userId,Integer friendId);

    // Câu lấy ra tất cả bạn chung giữa 2 user
    @Query("select myFriend.friend from Friends myFriend " +
            "inner join Friends yourFriend on myFriend.friend.userId = yourFriend.friend.userId " +
            "where myFriend.user.userId = ?1 and yourFriend.user.userId = ?2")
    List<User> findMutualFriend(Integer receiveUserId, Integer sendUserId);

    @Query("select c from Friends c where c.user.userId = ?1")
    List<Friends> findAllFriendByIdToCheck(Integer userId);

}
