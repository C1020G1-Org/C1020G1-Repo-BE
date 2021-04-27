package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.GroupRequest;

import c1020g1.social_network.model.User;
import c1020g1.social_network.repository.GroupRequestRepository;
import c1020g1.social_network.service.GroupRequestService;
import c1020g1.social_network.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupRequestServiceImpl implements GroupRequestService {
    @Autowired
    private GroupRequestRepository groupRequestRepository;
    @Autowired
    private GroupService groupService;

    @Override
    public String addGroupRequest(GroupRequest groupRequest) {
        if (groupService.findById(groupRequest.getGroup().getGroupId()) == null) {
            return "NG";
        }

        groupRequestRepository.save(groupRequest);
        return "OK";
    }

        @Override
        public GroupRequest findById (Integer id){
            return groupRequestRepository.findByGroupId(id);
        }

        @Override
        public void deleteById (Integer id){
            groupRequestRepository.deleteById(id);
        }

        @Override
        public Page<GroupRequest> findAllByGroupAndKey (Integer groupId, String key, Pageable pageable){
            return groupRequestRepository.findAllByGroupAndKey(groupId, key, pageable);
        }

        @Override
        public Page<GroupRequest> findAllByUser (User user, Pageable pageable){
            return groupRequestRepository.findAllByUser(user, pageable);
        }

        @Override
        public GroupRequest findExist (GroupRequest groupRequest){
            return groupRequestRepository.findExist(groupRequest.getGroup().getGroupId(), groupRequest.getUser().getUserId());
        }

        @Override
        public void save (GroupRequest groupRequest){
            groupRequestRepository.save(groupRequest);
        }
    }

