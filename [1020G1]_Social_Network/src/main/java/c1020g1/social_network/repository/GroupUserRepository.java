package c1020g1.social_network.repository;

import c1020g1.social_network.model.GroupUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, Integer> {
    @Query(value = "select * from group_user " +
            "join `user` u on u.user_id = group_user.user_id " +
            "where group_user.group_id = :id and u.username like %:key% " +
            "order by u.username", nativeQuery = true)
    Page<GroupUser> findAllByGroupAndUsernameContainingOrderByUsername(@Param("id") int id, @Param("key") String key,
                                                                       Pageable pageable);

    @Query(value = "select * from group_user where group_id = :group_id and user_id = :user_id", nativeQuery = true)
    GroupUser findExist(@Param("group_id") int groupId, @Param("user_id") int userId);
}