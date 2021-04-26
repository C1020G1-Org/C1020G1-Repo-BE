package c1020g1.social_network.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
>>>>>>> 2f10ba501fc9c4b5002eb908358863e91587f569

import javax.persistence.*;

@Entity
@Table(name = "parent_comment")
<<<<<<< HEAD
public class ParentComment {
=======
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "parentCommentId")
public class ParentComment implements Validator {
>>>>>>> 2f10ba501fc9c4b5002eb908358863e91587f569

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "parent_comment_id")
    private int parentCommentId;
    @Column(name = "content")
    private String content;
    @Column(name = "commentImage")
    private String commentImage;
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    @JsonBackReference
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

<<<<<<< HEAD
    public int getParentCommentId() {
=======
    @OneToMany(mappedBy = "parentComment")
    @JsonManagedReference
    private List<ChildComment> childComments;

    public Integer getParentCommentId() {
>>>>>>> 2f10ba501fc9c4b5002eb908358863e91587f569
        return parentCommentId;
    }

    public void setParentCommentId(int parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentImage() {
        return commentImage;
    }

    public void setCommentImage(String commentImage) {
        this.commentImage = commentImage;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
