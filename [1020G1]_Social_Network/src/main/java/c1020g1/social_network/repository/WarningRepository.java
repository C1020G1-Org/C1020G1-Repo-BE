package c1020g1.social_network.repository;

import c1020g1.social_network.model.GroupUser;
import c1020g1.social_network.model.GroupWarning;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface WarningRepository extends JpaRepository<GroupWarning,Integer> {
    Page<GroupWarning> findAllByGroupUserOrderByWarningDateDesc(GroupUser groupUser, Pageable pageable);

    @Modifying
    @Query(value = "delete from group_warning g where g.group_user_id = :id",nativeQuery = true)
    void deleteByGroupUserId(@Param("id") int id);
}