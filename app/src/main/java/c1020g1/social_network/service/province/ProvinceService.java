package c1020g1.social_network.service.province;

import c1020g1.social_network.model.Province;


import org.springframework.stereotype.Service;



@Service
public interface ProvinceService {

    //PhucPt
    Iterable<Province> getAllProvince();

}
