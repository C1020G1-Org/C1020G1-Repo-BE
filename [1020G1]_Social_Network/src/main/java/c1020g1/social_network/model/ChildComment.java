package c1020g1.social_network.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "child_comment")
public class ChildComment {

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
}
