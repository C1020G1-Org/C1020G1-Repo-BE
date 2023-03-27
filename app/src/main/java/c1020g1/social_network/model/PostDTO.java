package c1020g1.social_network.model;

import javax.validation.Valid;

public class PostDTO {
    @Valid
    private Post post;
    private String[] postImages;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String[] getPostImages() {
        return postImages;
    }

    public void setPostImages(String[] postImages) {
        this.postImages = postImages;
    }
}
