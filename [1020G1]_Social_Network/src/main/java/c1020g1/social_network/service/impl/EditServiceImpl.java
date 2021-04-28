package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.User;
import c1020g1.social_network.repository.EditRepository;
import c1020g1.social_network.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditServiceImpl implements EditService {
    @Autowired
    EditRepository editRepository;

    @Override
    public User getUserInfoById(Integer id) {
        return editRepository.findById(id).orElse(null);
    }

    @Override
    public void updateUser(User user) {
        editRepository.updateUser(user);
    }


}
