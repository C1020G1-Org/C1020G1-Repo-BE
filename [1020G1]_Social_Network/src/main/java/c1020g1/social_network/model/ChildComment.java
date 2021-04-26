package c1020g1.social_network.model;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

>>>>>>> post_management
import javax.persistence.*;

@Entity
@Table(name = "child_comment")
<<<<<<< HEAD
public class ChildComment {
=======
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "childCommentId")
public class ChildComment implements Validator {
>>>>>>> post_management

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_comment_id")
    private int childCommentId;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "comment_image")
    private String commentImage;
    @ManyToOne
    @JoinColumn(name = "parent_comment_id", referencedColumnName = "parent_comment_id")
    @JsonBackReference
    private ParentComment parentComment;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;

    public int getChildCommentId() {
        return childCommentId;
    }

    public void setChildCommentId(int childCommentId) {
        this.childCommentId = childCommentId;
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

    public ParentComment getParentComment() {
        return parentComment;
    }

    public void setParentComment(ParentComment parentComment) {
        this.parentComment = parentComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
