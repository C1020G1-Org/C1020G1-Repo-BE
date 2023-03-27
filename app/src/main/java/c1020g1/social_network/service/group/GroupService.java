package c1020g1.social_network.service.group;

import c1020g1.social_network.model.GroupSocial;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    Page<GroupSocial> findAllByGroupName(String key, Pageable pageable);

    Page<GroupSocial> findAll(Pageable pageable);

    void save(GroupSocial groupSocial);

    GroupSocial findById(Integer id);

    void remove(Integer id);
}
