package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.District;
import c1020g1.social_network.model.Province;
import c1020g1.social_network.model.User;
import c1020g1.social_network.model.Ward;
import c1020g1.social_network.repository.EditRepository;
import c1020g1.social_network.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditServiceImpl implements EditService {
    @Autowired
    EditRepository editRepository;

    @Override
    public User getUserInfoById(int id) {
        return editRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        editRepository.save(user);
    }


}
