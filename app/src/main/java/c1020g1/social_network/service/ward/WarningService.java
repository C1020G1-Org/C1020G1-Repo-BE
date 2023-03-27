package c1020g1.social_network.service.ward;

import c1020g1.social_network.model.GroupUser;
import c1020g1.social_network.model.GroupWarning;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarningService {
    void deleteByGroupUserId(int id);
    Page<GroupWarning> findAllByGroupUserOrderByWarningDateDesc(GroupUser groupUser, Pageable pageable);
    void save(GroupWarning warning);
}
