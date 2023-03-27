package c1020g1.social_network.repository;

import c1020g1.social_network.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    @Modifying
    @Query(value = "insert into post (post_content, post_status, post_published, user_id) values (:postContent, " +
            ":postStatus, :postPublished, :userId)", nativeQuery = true)
    void createPost(@Param("postContent") String postContent, @Param("postStatus") String postStatus, @Param("postPublished") Timestamp postPublished,
                    @Param("userId") Integer userId);

    @Modifying
    @Query(value = "insert into post (post_content, post_status, post_published, user_id, group_id) values (:postContent, " +
            ":postStatus, :postPublished, :userId, :groupId)", nativeQuery = true)
    void createPostInGroup(@Param("postContent") String postContent, @Param("postStatus") String postStatus, @Param("postPublished") Timestamp postPublished,
                           @Param("userId") Integer userId, @Param("groupId") Integer groupId);

    @Modifying
    @Query(value = "update post set post_content = ?1, post_status = ?2 where post_id = ?3", nativeQuery = true)
    void editPost(String postContent, String postStatus, Integer postId);
  
    @Query(value = "SELECT * \n" +
            "FROM post \n" +
            "WHERE post_id = ?1", nativeQuery = true)
    Post getPostById(Integer postId);

    @Query(value = "SELECT  \n" +
            "p.post_id, \n" +
            "p.post_content, \n" +
            "p.post_status, \n" +
            "p.post_published, \n" +
            "p.user_id, \n" +
            "p.group_id\n" +
            "FROM friends fr\n" +
            "JOIN user u USING (user_id)\n" +
            "JOIN post p ON fr.friend_id = p.user_id \n" +
            "WHERE fr.user_id = :userId\n" +
            "UNION \n" +
            "SELECT *\n" +
            "FROM post \n" +
            "WHERE user_id = :userId \n" +
            "ORDER BY post_published DESC ",countQuery= "SELECT count(*) from friends\n", nativeQuery = true)
    Page<Post> getAllPostInNewsFeed(@Param("userId") Integer userId, Pageable pageable);

    @Query(value = "SELECT *\n" +
            "FROM post\n" +
            "WHERE user_id = :userId", nativeQuery = true)
    Page<Post> getAllPostInWallUser(@Param("userId") Integer userId, Pageable pageable);

    @Query(value = "SELECT * FROM post WHERE post.user_id = :userId ORDER BY post.post_id DESC LIMIT 1", nativeQuery = true)
    Post getRecentPostByUserId(Integer userId);

    @Query("select p from Post p where p.groupSocial.groupId=?1")
    Page<Post> findAllPostGroup(Integer id, Pageable pageable);

    @Modifying
    @Query(value = "DELETE FROM post WHERE post_id = ?1", nativeQuery = true)
    void deletePostByID(Integer postId);
}
