package c1020g1.social_network.controller;

import c1020g1.social_network.model.District;
import c1020g1.social_network.model.Province;
import c1020g1.social_network.model.User;
import c1020g1.social_network.model.Ward;
import c1020g1.social_network.service.district.DistrictService;
import c1020g1.social_network.service.province.ProvinceService;
import c1020g1.social_network.service.group.WardService;
import c1020g1.social_network.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class EditController {
    @Autowired
    UserService userService;

    @Autowired
    WardService wardService;

    @Autowired
    DistrictService districtService;

    @Autowired
    ProvinceService provinceService;

    @PutMapping("user/edit")
    public ResponseEntity<?> save(@RequestBody User user) {
        user.setAccount(userService.getUserById(user.getUserId()).getAccount());
        userService.save( user );
        return ResponseEntity.ok(userService.getUserById(user.getUserId()));
    }

    @GetMapping("/user/ward")
    public List<Ward> getWard() {
        return wardService.getWardList();
    }

    @GetMapping("/user/district")
    public List<District> getDistrict() {
        return districtService.getDistrictList();
    }

    @GetMapping("/user/province")
    public Iterable<Province> getProvinceList() {
        return provinceService.getAllProvince();
    }
}


