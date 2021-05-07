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

    @Override
    public Post getPostById(Integer postId) {
        return postRepository.getPostById(postId);
    }

    @Override
    @Transactional
    public void createPost(Post post) {
        if (post.getGroup() == null) {
            postRepository.createPost(post.getPostContent(), post.getPostStatus(), post.getPostPublished(), post.getUser().getUserId());
        } else {
            postRepository.createPostInGroup(post.getPostContent(), post.getPostStatus(), post.getPostPublished(), post.getUser().getUserId(), post.getGroup().getGroupId());
        }
    }

    @Override
    @Transactional
    public void editPost(Post post) {
        postRepository.editPost(post.getPostContent(), post.getPostStatus(), post.getPostId());
    }

    @Override
    public Page<Post> getAllPostInNewsFeed(Integer userId, Pageable pageable) {
        return postRepository.getAllPostInNewsFeed(userId, pageable);
    }

    @Override
    public List<PostImage> getAllImageByPostId(Integer postId) {
        return postImageRepository.getAllImageByPostId(postId);

    }

    @Override
    public List<Post> findAllPostGroup(Integer id) {
        return postRepository.findAllPostGroup(id);
    }
}
