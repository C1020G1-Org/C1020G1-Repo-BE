package c1020g1.social_network.controller;

import c1020g1.social_network.model.District;
import c1020g1.social_network.model.Province;
import c1020g1.social_network.model.User;
import c1020g1.social_network.model.Ward;
import c1020g1.social_network.service.DistrictService;
import c1020g1.social_network.service.EditService;
import c1020g1.social_network.service.ProvinceService;
import c1020g1.social_network.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
public class EditController {
    @Autowired
    EditService editService;

    @Autowired
    WardService wardService;

    @Autowired
    DistrictService districtService;

    @Autowired
    ProvinceService provinceService;


    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Integer id) {
        return editService.getUserInfoById(id);
    }


    @PutMapping("user/edit/{userId}")
    public void save(@RequestBody @Valid User user, @PathVariable Integer userId) {
        editService.updateUser(user);
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
    public List<Province> getProvinceList() {
        return provinceService.getProvinceList();
    }
}


