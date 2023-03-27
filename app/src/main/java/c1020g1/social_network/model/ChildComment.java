package c1020g1.social_network.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "child_comment")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "childCommentId")
public class ChildComment implements Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_comment_id")
    private Integer childCommentId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "comment_image")
    private String commentImage;

    @Column(name = "comment_time")
    private Timestamp commentTime;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id", referencedColumnName = "parent_comment_id")
    @JsonBackReference
    private ParentComment parentComment;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;

    public Integer getChildCommentId() {
        return childCommentId;
    }

    public void setChildCommentId(Integer childCommentId) {
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

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ChildComment.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChildComment childComment =(ChildComment) target;

        if(childComment.getContent() == null && childComment.getCommentImage() == null)
            errors.reject("bad-request");
    }

}
