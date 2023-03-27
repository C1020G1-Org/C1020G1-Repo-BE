package c1020g1.social_network.service.group.imp;

import c1020g1.social_network.model.GroupRequest;
import c1020g1.social_network.model.User;
import c1020g1.social_network.repository.GroupRequestRepository;
import c1020g1.social_network.service.group.GroupRequestService;
import c1020g1.social_network.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupRequestServiceImpl implements GroupRequestService {
    @Autowired
    private GroupRequestRepository groupRequestRepository;

    @Override
    public GroupRequest findById(Integer id) {
        return groupRequestRepository.findByGroupId(id);
    }

    @Override
    public List<GroupRequest> findAllByUser(User user) {
        return groupRequestRepository.findAllByUser(user);
    }

    @Override
    public void deleteById(Integer id) {
        groupRequestRepository.deleteById(id);
    }

    @Override
    public Page<GroupRequest> findAllByGroupAndKey(Integer groupId, String key, Pageable pageable) {
        return groupRequestRepository.findAllByGroupAndKey(groupId, key, pageable);
    }

    @Override
    public GroupRequest findExist(GroupRequest groupRequest) {
        return groupRequestRepository.findExist(groupRequest.getGroupSocial().getGroupId(), groupRequest.getUser().getUserId());
    }

    @Override
    public void save(GroupRequest groupRequest) {
        groupRequestRepository.save(groupRequest);
    }

    @Override
    public List<GroupRequest> findGroupRequestByUserId(Integer userId) {
        return groupRequestRepository.findGroupRequestByUserId(userId);
    }
}

