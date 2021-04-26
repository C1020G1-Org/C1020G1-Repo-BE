package c1020g1.social_network.service.post;

import c1020g1.social_network.model.Post;
import c1020g1.social_network.model.PostImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    void createPost(Post post);

    void editPost(Post post);

    Post getPostById(Integer postId);

    Page<Post> getAllPostInNewsFeed(Integer userId, Pageable pageable);

    List<PostImage> getAllImageByPostId(Integer postId);

    List<Post> findAllPostGroup(Integer id);

}
