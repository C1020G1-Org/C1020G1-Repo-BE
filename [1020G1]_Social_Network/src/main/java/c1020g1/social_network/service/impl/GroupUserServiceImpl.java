package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.GroupUser;
import c1020g1.social_network.repository.GroupUserRepository;
import c1020g1.social_network.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class GroupUserServiceImpl implements GroupUserService {
    @Autowired
    private GroupUserRepository groupUserRepository;
    @Override
    public List<GroupUser> findAllGroupMember(Integer id) {
        return groupUserRepository.findAllGroupMember(id);
    }
    public Page<GroupUser> findAllByGroupAndUsernameContainingOrderByUsername(Integer id, String key, Pageable pageable) {
        return groupUserRepository.findAllByGroupAndUsernameContainingOrderByUsername(id,key,pageable);
    }
    @Override
    public void save(GroupUser groupUser) {
        groupUserRepository.save(groupUser);
    }

    @Override
    public void deleteById(Integer id) {
        groupUserRepository.deleteById(id);
    }

    @Override
    public GroupUser findById(Integer id) {
        return groupUserRepository.findById(id).orElse(null);
    }

    @Override
    public GroupUser findExist(Integer groupId,Integer userId){
        return groupUserRepository.findExist(groupId,userId);
    }

}
