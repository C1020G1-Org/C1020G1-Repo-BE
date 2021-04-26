package c1020g1.social_network.repository;

import c1020g1.social_network.model.ChildComment;
import c1020g1.social_network.model.ParentComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ChildCommentRepository extends JpaRepository<ChildComment, Integer> {
    @Query(value = "SELECT * \n" +
            "FROM child_comment\n" +
            "WHERE parent_comment_id = ?1 ", nativeQuery = true)
    List<ChildComment> getAllChildCommentByParentCommentId(Integer parentCommentId);

    @Query(value = "SELECT * \n" +
            "FROM child_comment \n" +
            "WHERE child_comment_id = ?1", nativeQuery = true)
    ChildComment getChildCommentById(Integer childCommentId);

    @Modifying
    @Query(value = "INSERT INTO child_comment(content, comment_image, parent_comment_id, user_id, comment_time)\n" +
            "VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void createChildComment(String content, String commentImage, Integer parentCommentId, Integer userId, Timestamp commentTime);

    @Modifying
    @Query(value = "UPDATE child_comment\n" +
            "SET content = ?1, comment_image = ?2 \n" +
            "WHERE child_comment_id = ?3", nativeQuery = true)
    void editChildComment(String content, String comment_image, Integer childCommentId);

    @Modifying
    @Query(value = "DELETE FROM child_comment\n" +
            "WHERE child_comment_id = ?1", nativeQuery = true)
    void removeChildComment(Integer childCommentId);
}
