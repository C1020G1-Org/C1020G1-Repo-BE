package c1020g1.social_network.service;

import c1020g1.social_network.model.GroupUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GroupUserService {
    List<GroupUser> findAllGroupMember(Integer id);
    Page<GroupUser> findAllByGroupAndUsernameContainingOrderByUsername(Integer id, String key, Pageable pageable);
    void save(GroupUser groupUser);
    void deleteById(Integer id);
    GroupUser findById(Integer id);
    GroupUser findExist(Integer groupId, Integer userId);
}
