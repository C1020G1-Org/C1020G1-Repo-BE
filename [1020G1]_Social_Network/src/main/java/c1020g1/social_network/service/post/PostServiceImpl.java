package c1020g1.social_network.service.post;

import c1020g1.social_network.model.Post;
import c1020g1.social_network.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import c1020g1.social_network.model.PostImage;
import c1020g1.social_network.repository.PostImageRepository;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    private PostImageRepository postImageRepository;

    @Override
    public Post getPostById(Integer postId) {
        return postRepository.getPostById(postId);
    }

    @Override
    @Transactional
    public void createPost(Post post) {
        if (post.getGroupSocial() == null) {
            postRepository.createPost(post.getPostContent(), post.getPostStatus(), post.getPostPublished(), post.getUser().getUserId());
        } else {
            postRepository.createPostInGroup(post.getPostContent(), post.getPostStatus(), post.getPostPublished(), post.getUser().getUserId(), post.getGroupSocial().getGroupId());
        }
    }

    @Override
    @Transactional
    public void editPost(Post post) {
        postRepository.editPost(post.getPostContent(), post.getPostStatus(), post.getPostId());
    }

    @Override
    public List<Post> getAllPostInNewsFeed(Integer userId) {
        List<Post> postsInWall = postRepository.getAllPostInWall(userId);

        List<Post> postsInGroupUser = postRepository.getAllPostInGroupUser(userId);

        List<Post> postsOfFriendUser = postRepository.getAllPostOfFriendUser(userId);

        List<Post> postsInNewsFeed = Stream.of(postsInWall, postsInGroupUser, postsOfFriendUser)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        postsInNewsFeed.sort(Comparator.comparing(Post::getPostPublished));

        return postsInNewsFeed;
    }

    @Override
    public List<PostImage> getAllImageByPostId(Integer postId) {
        return postImageRepository.getAllImageByPostId(postId);

    }

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

    @Override
    public Post getRecentPostByUserId(Integer userId) {
        return postRepository.getRecentPostByUserId(userId);
    }

    @Override
    @Transactional
    public void deletePostById(Integer postId) {
        postRepository.deletePostByID(postId);
    }
}
