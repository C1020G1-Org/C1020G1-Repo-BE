package c1020g1.social_network.service.group;

import c1020g1.social_network.model.Ward;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WardService {
    //ThinhTHb
    List<Ward> getWardList();

    //PhucPT
    Iterable<Ward> getWardByDistrictId(int district);
}
