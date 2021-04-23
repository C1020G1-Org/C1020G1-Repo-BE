package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.GroupRequest;
import c1020g1.social_network.model.User;
import c1020g1.social_network.repository.GroupRequestRepository;
import c1020g1.social_network.service.GroupRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupRequestServiceImpl implements GroupRequestService {
    @Autowired
    private GroupRequestRepository groupRequestRepository;

    @Override
    public GroupRequest findById(int id) {
        return groupRequestRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        groupRequestRepository.deleteById(id);
    }

    @Override
    public Page<GroupRequest> findAllByGroupAndKey(int groupId, String key, Pageable pageable) {
        return groupRequestRepository.findAllByGroupAndKey(groupId,key,pageable);
    }

    @Override
    public Page<GroupRequest> findAllByUser(User user, Pageable pageable) {
        return groupRequestRepository.findAllByUser(user,pageable);
    }

    @Override
    public GroupRequest findExist(GroupRequest groupRequest) {
        return groupRequestRepository.findExist(groupRequest.getGroup().getGroupId(),groupRequest.getUser().getUserId());
    }

    @Override
    public void save(GroupRequest groupRequest) {
        groupRequestRepository.save(groupRequest);
    }
}