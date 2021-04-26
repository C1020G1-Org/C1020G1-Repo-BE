package c1020g1.social_network.service;

import c1020g1.social_network.model.Province;
import c1020g1.social_network.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public Iterable<Province> getAllProvince() {
        return provinceRepository.findAll();
    }
}
