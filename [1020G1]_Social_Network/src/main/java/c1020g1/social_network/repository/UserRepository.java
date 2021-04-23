package c1020g1.social_network.repository;

import c1020g1.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from user where user_id = :id", nativeQuery = true)
    User findByUserId(@Param("id") int id);

    @Query(value = "select * from `user` u " +
            "where u.user_id in " +
            "(SELECT distinct af.friend_id from friends f join friends af on f.friend_id = af.user_id " +
            "where af.friend_id not in (select g.user_id from group_user g where g.group_id = :id)" +
            "and af.friend_id not in (select r.user_id from group_request r where r.group_id = :id))", nativeQuery = true)
    List<User> inviteFriendsOfFriends(@Param("id") int groupId);

    @Query(value = "select * from `user` u where u.user_id in (select f.friend_id from friends f " +
            "where f.user_id = :user_id and f.friend_id not in " +
            "(select g.user_id from group_user g where g.group_id = :id) and f.friend_id not in " +
            "(select r.user_id from group_request r where r.group_id = :id))",nativeQuery = true)
    List<User> inviteFriends(@Param("id") int groupId,@Param("user_id") int userId);
}