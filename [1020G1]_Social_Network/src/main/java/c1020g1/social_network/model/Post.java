package c1020g1.social_network.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
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
    @NotBlank(message = "Content not blank!!")
    private String postContent;

    @Column(name = "post_status")
    @NotBlank(message = "Status not blank!!")
    private String postStatus;

    @Column(name = "post_published")
    private Timestamp postPublished;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private GroupSocial groupSocial;

    @OneToMany(mappedBy = "post")
    private List<ParentComment> parentComments;

    @OneToMany(mappedBy = "post")
    private List<PostImage> postImages;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
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

    public Timestamp getPostPublished() {
        return postPublished;
    }

    public void setPostPublished(Timestamp postPublished) {
        this.postPublished = postPublished;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GroupSocial getGroupSocial() {
        return groupSocial;
    }

    public void setGroupSocial(GroupSocial groupSocial) {
        this.groupSocial = groupSocial;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postContent='" + postContent + '\'' +
                ", postStatus='" + postStatus + '\'' +
                ", postPublished=" + postPublished +
                ", user=" + user +
                ", groupSocial=" + groupSocial +
                '}';
    }

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
}
