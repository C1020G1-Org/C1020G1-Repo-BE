package c1020g1.social_network.service.post;

import c1020g1.social_network.model.Post;
import c1020g1.social_network.model.PostImage;
import c1020g1.social_network.repository.PostImageRepository;
import c1020g1.social_network.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
  
    @Autowired
    private PostImageRepository postImageRepository;

    /**
     * Author : CaoLPT
     * get post
     * @param postId
     */
    @Override
    public Post getPostById(Integer postId) {
        return postRepository.getPostById(postId);
    }

    /**
     * Author : CaoLPT
     *create post
     * @param post
     */
    @Override
    @Transactional
    public void createPost(Post post) {
        if (post.getGroupSocial() == null) {
            postRepository.createPost(post.getPostContent(), post.getPostStatus(), post.getPostPublished(), post.getUser().getUserId());
        } else {
            postRepository.createPostInGroup(post.getPostContent(), post.getPostStatus(), post.getPostPublished(), post.getUser().getUserId(), post.getGroupSocial().getGroupId());
        }
    }

    /**
     * Author : CaoLPT
     * edit post
     * @param post
     */
    @Override
    @Transactional
    public void editPost(Post post) {
        postRepository.editPost(post.getPostContent(), post.getPostStatus(), post.getPostId());
    }

    /**
     * Author : CaoLPT
     * get all posts in news feed
     * @param userId
     * @param pageable
     */
    @Override
    public Page<Post> getAllPostInNewsFeed(Integer userId, Pageable pageable) {
        return postRepository.getAllPostInNewsFeed(userId, pageable);
    }

    /**
     * Author : CaoLPT
     * get all image of post
     * @param postId
     */
    @Override
    public List<PostImage> getAllImageByPostId(Integer postId) {
        return postImageRepository.getAllImageByPostId(postId);

    }

    /**
     * Author : DungHA
     * get all posts in wall of user
     * @param userId
     */
    @Override
    public List<Post> getAllPostInWallUser(Integer userId) {
        return postRepository.getAllPostInWallUser(userId);
    }

}
