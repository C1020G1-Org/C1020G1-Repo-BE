package c1020g1.social_network.repository;

import c1020g1.social_network.model.ParentComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ParentCommentRepository extends JpaRepository<ParentComment, Integer> {
    @Query(value = "SELECT * \n" +
            "FROM parent_comment\n" +
            "WHERE post_id = ?1 ", nativeQuery = true)
    List<ParentComment> getAllParentCommentByPostId(Integer postId);

    @Query(value = "SELECT * \n" +
            "FROM parent_comment \n" +
            "WHERE parent_comment_id = ?1", nativeQuery = true)
    ParentComment getParentCommentById(Integer parentCommentId);

    @Modifying
    @Query(value = "INSERT INTO parent_comment (content, comment_image, post_id, user_id, comment_time)\n" +
            "VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void createParentComment(String content, String comment_image, Integer postId, Integer userId, Timestamp commentTime);


    @Modifying
    @Query(value = "UPDATE parent_comment \n" +
            "SET content = ?1, comment_image = ?2 \n" +
            "WHERE parent_comment_id = ?3", nativeQuery = true)
    void editParentComment(String content, String comment_image, Integer parentCommentId);

    @Modifying
    @Query(value = "DELETE FROM parent_comment\n" +
            "WHERE parent_comment_id = ?1", nativeQuery = true)
    void removeParentComment(Integer parentCommentId);

    @Query(value = "SELECT * FROM parent_comment WHERE post_id = :postId ORDER BY parent_comment_id DESC LIMIT 1", nativeQuery = true)
    ParentComment getRecentParentComment(Integer postId);
}
