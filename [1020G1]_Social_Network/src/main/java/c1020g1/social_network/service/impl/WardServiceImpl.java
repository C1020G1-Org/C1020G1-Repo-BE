package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.Ward;
import c1020g1.social_network.repository.WardRepository;
import c1020g1.social_network.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardServiceImpl implements WardService {
    @Autowired
    WardRepository wardRepository;

    @Override
    public List<Ward> getWardList() {
        return wardRepository.getWardList();
    }

}
