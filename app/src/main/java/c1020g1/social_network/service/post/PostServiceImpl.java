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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
     *
     * @param postId
     */
    @Override
    public Post getPostById(Integer postId) {
        return postRepository.getPostById(postId);
    }

    /**
     * Author : SonPH
     * create post
     *
     * @param post
     */
    @Override
    @Transactional
    public void createPost(Post post) {
        if (post.getGroupSocial() == null) {
            postRepository.createPost(encodeStringUrl(post.getPostContent()), post.getPostStatus(), post.getPostPublished(), post.getUser().getUserId());
        } else {
            postRepository.createPostInGroup(encodeStringUrl(post.getPostContent()), post.getPostStatus(), post.getPostPublished(), post.getUser().getUserId(), post.getGroupSocial().getGroupId());
        }
    }

    /**
     * Author : SonPH
     * edit post
     *
     * @param post
     */
    @Override
    @Transactional
    public void editPost(Post post) {
        postRepository.save(post);
    }

    /**
     * Author : CaoLPT
     * get all posts in news feed
     *
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
     *
     * @param postId
     */
    @Override
    public List<PostImage> getAllImageByPostId(Integer postId) {
        return postImageRepository.getAllImageByPostId(postId);

    }

    /**
     * Author : DungHA
     * get all posts in wall of user
     *
     * @param userId
     */
    @Override
    public Page<Post> getAllPostInWallUser(Integer userId, Pageable pageable) {
        return postRepository.getAllPostInWallUser(userId, pageable);
    }

    /**
     * Author : SonPH
     * decode string URL
     *
     * @param encodedUrl
     */
    @Override
    public String decodeStringUrl(String encodedUrl) {
        String decodedUrl = null;
        try {
            decodedUrl = URLDecoder.decode(encodedUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return decodedUrl;
        }
        return decodedUrl;
    }

    /**
     * Author : SonPH
     * decode string URL
     *
     * @param url
     */
    @Override
    public String encodeStringUrl(String url) {
        String encodedUrl = null;
        try {
            encodedUrl = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return encodedUrl;
        }
        return encodedUrl;
    }

    /**
     * Author : DungHA
     *
     * @param userId
     */
    @Override
    public Post getRecentPostByUserId(Integer userId) {
        return postRepository.getRecentPostByUserId(userId);
    }

    /**
     * Author : CuongNVM
     *
     * @param id
     */
    @Override
    public Page<Post> findAllPostGroup(Integer id,Pageable pageable) {
        return postRepository.findAllPostGroup(id, pageable);
    }

    @Override
    public void createPostInGroup(Post post) {
        postRepository.createPostInGroup(encodeStringUrl(post.getPostContent()), post.getPostStatus(), post.getPostPublished(), post.getUser().getUserId(), post.getGroupSocial().getGroupId());

    }

    @Override
    @Transactional
    public void deletePostById(Integer postId) {
        postRepository.deletePostByID(postId);
    }
}
