package c1020g1.social_network.service.post_image;

import c1020g1.social_network.model.PostImage;
import c1020g1.social_network.repository.PostImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostImageServiceImpl implements PostImageService {
    @Autowired
    PostImageRepository postImageRepository;

    @Override
    public List<PostImage> getAllImageByPostId(Integer postId) {
        return postImageRepository.getAllImageByPostId(postId);
    }

    @Override
    @Transactional
    public void createPostImage(Integer postId, String image) {
        postImageRepository.createImagePost(postId, image);
    }

    @Override
    public void deletePostImage(Integer postImageId) {
        postImageRepository.deleteImagePost(postImageId);
    }
}
