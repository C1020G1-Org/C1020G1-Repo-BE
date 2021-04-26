package c1020g1.social_network.repository;

import c1020g1.social_network.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = " select p from Post p " +
            "where p.postId = ?1 ")
    List<Post> findPostByPostId(Integer id);
}
