package c1020g1.social_network.service;

import c1020g1.social_network.model.GroupRequest;

import c1020g1.social_network.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface GroupRequestService {
    GroupRequest findById(Integer id);
    void deleteById(Integer id);
    Page<GroupRequest> findAllByGroupAndKey(Integer groupId,String key, Pageable pageable);
    Page<GroupRequest> findAllByUser(User user, Pageable pageable);
    GroupRequest findExist(GroupRequest groupRequest);
    void save(GroupRequest groupRequest);
    String addGroupRequest(GroupRequest groupRequest);
}
