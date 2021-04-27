package c1020g1.social_network.service.post_image;

import c1020g1.social_network.model.PostImage;

import java.util.List;

public interface PostImageService {

    List<PostImage> getAllImageByPostId(Integer postId);

    void createPostImage(Integer postId, String image);

    void deletePostImage(Integer postImageId);
}
