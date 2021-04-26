package c1020g1.social_network.model;

import javax.persistence.*;
<<<<<<< HEAD
import java.util.Date;
=======
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
>>>>>>> post_management
import java.util.List;

@Entity
@Table(name = "post")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "postId")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;
    @Column(name = "post_content")
    private String postContent;
    @Column(name = "post_status")
    private String postStatus;
    @Column(name = "post_published")
    private Date postPublished;
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
    @OneToMany(mappedBy = "post")
<<<<<<< HEAD
    List<PostImage> postImages;

    @OneToMany(mappedBy = "post")
    List<ParentComment> parentComments;

    public List<ParentComment> getParentComments() {
        return parentComments;
    }

    public void setParentComments(List<ParentComment> parentComments) {
        this.parentComments = parentComments;
    }

    public List<PostImage> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<PostImage> postImages) {
        this.postImages = postImages;
    }

    public int getPostId() {
=======
    private List<ParentComment> parentComments;

    public Integer getPostId() {
>>>>>>> post_management
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public Date getPostPublished() {
        return postPublished;
    }

    public void setPostPublished(Date postPublished) {
        this.postPublished = postPublished;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
