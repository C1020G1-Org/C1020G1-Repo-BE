package c1020g1.social_network.repository;

import c1020g1.social_network.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT * " +
            "FROM post " +
            "WHERE user_id = ?1 AND group_id IS NULL", nativeQuery = true)
    List<Post> getAllPostInWall(Integer userId);

    @Query(value = "SELECT * " +
            "FROM post " +
            "WHERE user_id = ?1 AND group_id IS NOT NULL ", nativeQuery = true)
    List<Post> getAllPostInGroupUser(Integer userId);

    @Query(value= "SELECT " +
            "    p.post_id, " +
            "    p.post_content, " +
            "    p.post_status, " +
            "    p.post_published, " +
            "    p.user_id, " +
            "    p.group_id " +
            "FROM friends f " +
            "JOIN user u USING (user_id) " +
            "JOIN post p ON f.friend_id = p.user_id " +
            "WHERE f.user_id = ?1 ", nativeQuery = true)
    List<Post> getAllPostOfFriendUser(Integer userId);
}
