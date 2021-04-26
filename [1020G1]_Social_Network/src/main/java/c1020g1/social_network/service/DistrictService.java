package c1020g1.social_network.service;

import c1020g1.social_network.model.District;

public interface DistrictService {

    Iterable<District> getDistrictByProvinceId(int provinceId);
}
