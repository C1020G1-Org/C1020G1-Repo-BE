package c1020g1.social_network.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@Entity
<<<<<<< HEAD:[1020G1]_Social_Network/src/main/java/c1020g1/social_network/model/Group.java
@Table(name = "[group]")
public class Group {

=======
@Table(name = "group_social")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "groupId")
public class GroupSocial {
>>>>>>> post_management:[1020G1]_Social_Network/src/main/java/c1020g1/social_network/model/GroupSocial.java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_published")
    private Date groupPublished;

    @Column(name = "group_background")
    private String imageBackground;

    @Column(name = "group_avatar")
    private String imageAvatarUrl;

    @ManyToOne
    @JoinColumn(name = "admin", referencedColumnName = "user_id")
    private User admin;

    @Column(name = "scope")
    private String scope;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getGroupPublished() {
        return groupPublished;
    }

    public void setGroupPublished(Date groupPublished) {
        this.groupPublished = groupPublished;
    }

    public String getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(String imageBackground) {
        this.imageBackground = imageBackground;
    }

    public String getImageAvatarUrl() {
        return imageAvatarUrl;
    }

    public void setImageAvatarUrl(String imageAvatarUrl) {
        this.imageAvatarUrl = imageAvatarUrl;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}