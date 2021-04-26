package c1020g1.social_network.service;

import c1020g1.social_network.model.Ward;
import c1020g1.social_network.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WardServiceImpl implements WardService {

    @Autowired
    private WardRepository wardRepository;

    @Override
    public Iterable<Ward> getWardByDistrictId(int districtId) {
        return wardRepository.findAllByDistrictId(districtId);
    }
}
