package c1020g1.social_network.model;

import javax.persistence.*;

@Entity
@Table(name = "group_request")
public class GroupRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_request_id")
    private int groupRequestId;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private GroupSocial groupSocial;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    @Column(name = "from")
    private String from;

    public int getGroupRequestId() {
        return groupRequestId;
    }

    public void setGroupRequestId(int groupRequestId) {
        this.groupRequestId = groupRequestId;
    }

    public GroupSocial getGroupSocial() {
        return groupSocial;
    }

    public void setGroupSocial(GroupSocial groupSocial) {
        this.groupSocial = groupSocial;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
