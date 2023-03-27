package c1020g1.social_network.service.group;

import c1020g1.social_network.model.GroupUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupUserService {
    Page<GroupUser> findAllGroupMember(Integer id, Pageable pageable);
    Page<GroupUser> findAllByGroupAndUsernameContainingOrderByUsername(Integer id, String key, Pageable pageable);
    void save(GroupUser groupUser);
    void deleteById(Integer id);
    GroupUser findById(Integer id);
    GroupUser findExist(Integer groupId, Integer userId);
}
