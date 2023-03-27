package c1020g1.social_network.model.dto;

import c1020g1.social_network.model.GroupSocial;
import c1020g1.social_network.model.GroupUser;

public class GroupStatusDTO {
    GroupSocial groupSocial;
    GroupUser groupUser;

    public GroupSocial getGroupSocial() {
        return groupSocial;
    }

    public void setGroupSocial(GroupSocial groupSocial) {
        this.groupSocial = groupSocial;
    }

    public GroupUser getGroupUser() {
        return groupUser;
    }

    public void setGroupUser(GroupUser groupUser) {
        this.groupUser = groupUser;
    }
}
