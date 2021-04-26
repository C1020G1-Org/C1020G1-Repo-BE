package c1020g1.social_network.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "post_image")
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_image_id")
    private int postImageId;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    @JsonBackReference
    private Post post;
    @Column(name = "image")
    private String image;


    public int getPostImageId() {
        return postImageId;
    }

    public void setPostImageId(int postImageId) {
        this.postImageId = postImageId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
