package c1020g1.social_network.service.group;

import c1020g1.social_network.model.GroupRequest;

import c1020g1.social_network.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupRequestService {
    List<GroupRequest> findAllByUser(User user);

    GroupRequest findById(Integer id);

    void deleteById(Integer id);

    Page<GroupRequest> findAllByGroupAndKey(Integer groupId, String key, Pageable pageable);

    GroupRequest findExist(GroupRequest groupRequest);

    void save(GroupRequest groupRequest);

    List<GroupRequest> findGroupRequestByUserId(Integer userId);
}
