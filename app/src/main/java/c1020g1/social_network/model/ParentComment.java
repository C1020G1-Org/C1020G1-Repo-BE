package c1020g1.social_network.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "parent_comment")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "parentCommentId")
public class ParentComment implements Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_comment_id")
    private Integer parentCommentId;

    @Column(name = "content")
    private String content;

    @Column(name = "comment_image")
    private String commentImage;

    @Column(name = "comment_time")
    private Timestamp commentTime;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    @JsonBackReference
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "parentComment")
    @JsonManagedReference
    private List<ChildComment> childComments;

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
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

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public List<ChildComment> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<ChildComment> childComments) {
        this.childComments = childComments;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ParentComment.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ParentComment parentComment = (ParentComment) target;

        if (parentComment.getContent() == null && parentComment.getCommentImage() == null)
            errors.reject("bad-request");
    }
}
