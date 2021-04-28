package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.District;
import c1020g1.social_network.repository.DistrictRepository;
import c1020g1.social_network.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictRepository districtRepository;

    /**
     * Method: get list district
     * Author: ThinhTHB
     * @return
     */
    @Override
    public List<District> getDistrictList() {
        return districtRepository.getDistrictList();
    }

    /**
     * author: PhucPT
     * method: get iterables of district by province id
     * @param provinceId
     * @return
     */
    @Override
    public Iterable<District> getDistrictByProvinceId(int provinceId) {
        return districtRepository.findAllByProvinceId(provinceId);

    }
}
