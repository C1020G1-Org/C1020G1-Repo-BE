package c1020g1.social_network.service;

import c1020g1.social_network.model.Ward;

public interface WardService {
    Iterable<Ward> getWardByDistrictId(int district);
}
