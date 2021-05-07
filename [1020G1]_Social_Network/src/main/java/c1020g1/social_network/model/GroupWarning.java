package c1020g1.social_network.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "group_warning")
public class GroupWarning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_warning_id")
    private Integer groupWarningId;

    @Column(name = "warning_content")
    private String warningContent;

    @ManyToOne
    @JoinColumn(name = "group_user_id", referencedColumnName = "group_user_id")
    private GroupUser groupUser;

    @Column(name = "warning_date",nullable = false)
    private Date warningDate;

    public Integer getGroupWarningId() {
        return groupWarningId;
    }

    public void setGroupWarningId(Integer groupWarningId) {
        this.groupWarningId = groupWarningId;
    }

    public String getWarningContent() {
        return warningContent;
    }

    public void setWarningContent(String warningContent) {
        this.warningContent = warningContent;
    }

    public GroupUser getGroupUser() {
        return groupUser;
    }

    public void setGroupUser(GroupUser groupUser) {
        this.groupUser = groupUser;
    }

    public Date getWarningDate() {
        return warningDate;
    }

    public void setWarningDate(Date warningDate) {
        this.warningDate = warningDate;
    }
}
