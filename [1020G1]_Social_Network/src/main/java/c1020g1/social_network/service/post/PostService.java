package c1020g1.social_network.service.post;

import c1020g1.social_network.model.Post;
import c1020g1.social_network.model.PostImage;
import c1020g1.social_network.model.User;

import java.util.List;

public interface PostService {

    void createPost(Post post);

    void editPost(Post post);

    Post getPostById(Integer postId);

    List<Post> getAllPostInNewsFeed(Integer userId);

    List<PostImage> getAllImageByPostId(Integer postId);

    String encodeStringUrl(String url);

    String decodeStringUrl(String encodedUrl);

    Post getRecentPostByUserId(Integer userId);

    void deletePostById(Integer postId);
}
