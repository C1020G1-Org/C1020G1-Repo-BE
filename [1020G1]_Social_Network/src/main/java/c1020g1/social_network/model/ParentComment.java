package c1020g1.social_network.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "parent_comment")
public class ParentComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "parent_comment_id")
    private Integer parentCommentId;

    @Column(name = "content")
    private String content;

    @Column(name = "comment_image")
    private String commentImage;

    @Column(name = "comment_time")
    private Timestamp commentTime;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

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

    @Override
    public String toString() {
        return "ParentComment{" +
                "parentCommentId=" + parentCommentId +
                ", content='" + content + '\'' +
                ", commentImage='" + commentImage + '\'' +
                ", commentTime=" + commentTime +
                ", post=" + post +
                ", user=" + user +
                '}';
    }
}
