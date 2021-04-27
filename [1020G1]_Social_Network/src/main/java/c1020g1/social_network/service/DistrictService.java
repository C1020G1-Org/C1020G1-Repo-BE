package c1020g1.social_network.service;

import c1020g1.social_network.model.District;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DistrictService {
    //ThinhTHB
    List<District> getDistrictList();

    //PhucPt
    Iterable<District> getDistrictByProvinceId(int provinceId);

}
