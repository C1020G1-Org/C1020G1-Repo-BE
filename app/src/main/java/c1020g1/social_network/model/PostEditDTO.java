package c1020g1.social_network.model;

import javax.validation.Valid;
import java.util.List;

public class PostEditDTO {
    @Valid
    private Post post;

    private List<PostImage> postImages;
    private List<PostImage> deleteImages;
    private List<PostImage> updateImages;
    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }
    public List<PostImage> getPostImages() {
        return postImages;
    }
    public void setPostImages(List<PostImage> postImages) {
        this.postImages = postImages;
    }
    public List<PostImage> getDeleteImages() {
        return deleteImages;
    }
    public void setDeleteImages(List<PostImage> deleteImages) {
        this.deleteImages = deleteImages;
    }
    public List<PostImage> getUpdateImages() {
        return updateImages;
    }
    public void setUpdateImages(List<PostImage> updateImages) {
        this.updateImages = updateImages;
    }
}
