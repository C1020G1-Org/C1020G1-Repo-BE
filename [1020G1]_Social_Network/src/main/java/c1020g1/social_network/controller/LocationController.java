package c1020g1.social_network.controller;

import c1020g1.social_network.model.District;
import c1020g1.social_network.model.Province;
import c1020g1.social_network.model.Ward;
import c1020g1.social_network.service.DistrictService;
import c1020g1.social_network.service.ProvinceService;
import c1020g1.social_network.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
@CrossOrigin("http://localhost:4200")
public class LocationController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private WardService wardService;

    @GetMapping("/province")
    public ResponseEntity<Iterable<Province>> listProvinces() {
        Iterable<Province> provinces = provinceService.getAllProvince();
        return new ResponseEntity<>(provinces, HttpStatus.OK);
    }

    @GetMapping("/district/{id}")
    public ResponseEntity<Iterable<District>> listDistrictByProvinceId(@PathVariable("id") int provinceId) {
        Iterable<District> districts = districtService.getDistrictByProvinceId(provinceId);
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    @GetMapping("/ward/{id}")
    public ResponseEntity<Iterable<Ward>> listWardByDistrictId(@PathVariable("id") int districtId) {
        Iterable<Ward> wards = wardService.getWardByDistrictId(districtId);
        return new ResponseEntity<>(wards, HttpStatus.OK);
    }
}
