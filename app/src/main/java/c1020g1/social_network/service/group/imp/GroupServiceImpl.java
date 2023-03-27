package c1020g1.social_network.service.group.imp;

import c1020g1.social_network.model.GroupSocial;
import c1020g1.social_network.repository.GroupRepository;
import c1020g1.social_network.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Page<GroupSocial> findAll(Pageable pageable) {
        return groupRepository.findAllGroup(pageable);
    }

    @Override
    public void save(GroupSocial group) {
        groupRepository.updateGroup(group.getImageBackground(), group.getImageAvatarUrl(), group.getScope(), group.getGroupId());
    }


    @Override
    public GroupSocial findById(Integer id) {
        return groupRepository.findGroupById(id);
    }


    @Override
    public void remove(Integer id) {
        groupRepository.deleteGroupByGroupId(id);
    }

    @Override
    public Page<GroupSocial> findAllByGroupName(String key, Pageable pageable) {
        return groupRepository.findGroupByGroupNameContaining(key, pageable);
    }
}
