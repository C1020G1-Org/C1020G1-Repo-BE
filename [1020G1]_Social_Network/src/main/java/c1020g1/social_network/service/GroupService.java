package c1020g1.social_network.service;


import c1020g1.social_network.model.GroupSocial;

import java.util.List;

public interface GroupService {
    List<GroupSocial> findAll();

    List<GroupSocial> findGroupByNameContaining(String name);

    void save(GroupSocial groupSocial);

    GroupSocial findById(Integer id);

    void remove(Integer id);
}
