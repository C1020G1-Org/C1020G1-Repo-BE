package c1020g1.social_network.service;

import c1020g1.social_network.model.District;
import c1020g1.social_network.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public Iterable<District> getDistrictByProvinceId(int provinceId) {
        return districtRepository.findAllByProvinceId(provinceId);
    }
}
