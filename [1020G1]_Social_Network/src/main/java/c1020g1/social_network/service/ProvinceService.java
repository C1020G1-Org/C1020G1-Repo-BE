package c1020g1.social_network.service;

import c1020g1.social_network.model.Province;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProvinceService {

    List<Province> getProvinceList();
}
