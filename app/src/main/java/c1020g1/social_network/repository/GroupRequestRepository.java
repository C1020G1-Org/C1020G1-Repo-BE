package c1020g1.social_network.repository;

import c1020g1.social_network.model.GroupRequest;
import c1020g1.social_network.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface GroupRequestRepository extends JpaRepository<GroupRequest, Integer> {
    @Query(value = "select * from group_request where group_request_id = :id",nativeQuery = true)
    GroupRequest findByGroupId(@Param("id") Integer id);
    @Modifying
    @Query(value = "delete from group_request where group_request_id = :id",nativeQuery = true)
    void deleteById(@Param("id") Integer id);
    @Query(value = "select * from group_request r " +
            "join `user` u on u.user_id = r.user_id " +
            "where r.group_id = :group_id and r.sender = 'user'" +
            "and u.username like %:key%", nativeQuery = true)
    Page<GroupRequest> findAllByGroupAndKey(@Param("group_id") Integer groupId, @Param("key") String key, Pageable pageable);

    List<GroupRequest> findAllByUser(User user);
    @Query(value = "select * from group_request r where r.group_id = :group_id and r.user_id = :user_id",nativeQuery = true)
    GroupRequest findExist(@Param("group_id") Integer groupId,@Param("user_id") Integer userId);

    @Query("select r from GroupRequest r where r.user.userId=?1")
    List<GroupRequest> findGroupRequestByUserId(Integer userId);

}
