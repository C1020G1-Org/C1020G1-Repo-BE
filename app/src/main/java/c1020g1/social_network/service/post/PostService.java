package c1020g1.social_network.service.post;

import c1020g1.social_network.model.Post;
import c1020g1.social_network.model.PostImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    /**
     * Author : CaoLPT
     *create post
     * @param post
     */
    void createPost(Post post);

    /**
     * Author : CaoLPT
     * edit post
     * @param post
     */
    void editPost(Post post);

    /**
     * Author : CaoLPT
     * get post
     * @param postId
     */
    Post getPostById(Integer postId);

    /**
     * Author : CaoLPT
     * get all posts in news feed
     * @param userId
     * @param pageable
     */
    Page<Post> getAllPostInNewsFeed(Integer userId, Pageable pageable);

    /**
     * Author : CaoLPT
     * get all image of post
     * @param postId
     */
    List<PostImage> getAllImageByPostId(Integer postId);

    /**
     * Author : DungHA
     * get all posts in wall of user
     * @param userId
     */
    Page<Post> getAllPostInWallUser(Integer userId, Pageable pageable);

    String decodeStringUrl(String encodedUrl);

    Post getRecentPostByUserId(Integer userId);

    Page<Post> findAllPostGroup(Integer id,Pageable pageable);

    /**
     * Author : CaoLPT
     *create post in group
     * @param post
     */
    void createPostInGroup(Post post);



    String encodeStringUrl(String url);

    void deletePostById(Integer postId);
}
