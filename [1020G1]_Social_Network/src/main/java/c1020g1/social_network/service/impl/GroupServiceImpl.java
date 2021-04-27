package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.GroupSocial;
import c1020g1.social_network.repository.GroupRepository;
import c1020g1.social_network.service.GroupService;
import c1020g1.social_network.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupUserService groupUserService;

    @Override
    public List<GroupSocial> findAll() {
        return groupRepository.findAllGroup();
    }

    @Override
    public List<GroupSocial> findGroupByNameContaining(String name) {
        return groupRepository.findGroupByGroupNameContaining(name);
    }

    @Override
    public void save(GroupSocial group) {
        groupRepository.updateGroup(group.getImageBackground(), group.getImageAvatarUrl(), group.getScope(), group.getGroupId());
    }

    //
//    @Override
//    public List<GroupUser> findAllGroupMember(Integer id) {
//        return groupRepository.findAllGroupMember(id);
//    }
    @Override
    public GroupSocial findById(Integer id) {
        return groupRepository.findGroupById(id);
    }


    @Override
    public void remove(Integer id) {
        // thêm điều kiện kiểm tra user hiện tại có phải admin ko để hiện nút xoá group
        // Đợi merge code UserService
//        if ()
        groupRepository.deleteGroupByGroupId(id);
    }
}
