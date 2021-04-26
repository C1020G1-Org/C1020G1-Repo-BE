package c1020g1.social_network.repository;

import c1020g1.social_network.model.ParentComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<ParentComment , Integer>{
    @Query(value = "select p from ParentComment p where p.parentCommentId = ?1")
    List<ParentComment> findParentCommentByParentCommentId(Integer id);
}
