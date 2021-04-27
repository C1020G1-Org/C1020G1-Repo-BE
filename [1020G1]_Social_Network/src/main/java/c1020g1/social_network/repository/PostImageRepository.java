package c1020g1.social_network.repository;

import c1020g1.social_network.model.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Integer> {

    @Query(value = "SELECT * " +
            "FROM post_image " +
            "WHERE post_id = ?1", nativeQuery = true)
    List<PostImage> getAllImageByPostId(Integer postId);
}
