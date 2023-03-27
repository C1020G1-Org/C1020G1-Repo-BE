package c1020g1.social_network.service.group.imp;

import c1020g1.social_network.model.GroupUser;
import c1020g1.social_network.model.GroupWarning;
import c1020g1.social_network.repository.WarningRepository;
import c1020g1.social_network.service.ward.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WarningServiceImpl implements WarningService {
    @Autowired
    private WarningRepository warningRepository;
    @Override
    public void deleteByGroupUserId(int id) {
        warningRepository.deleteByGroupUserId(id);
    }

    @Override
    public Page<GroupWarning> findAllByGroupUserOrderByWarningDateDesc(GroupUser groupUser, Pageable pageable) {
        return warningRepository.findAllByGroupUserOrderByWarningDateDesc(groupUser,pageable);
    }

    @Override
    public void save(GroupWarning warning) {
        warningRepository.save(warning);
    }
}
