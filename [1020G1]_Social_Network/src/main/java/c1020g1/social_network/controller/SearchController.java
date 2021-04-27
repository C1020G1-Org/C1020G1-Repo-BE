package c1020g1.social_network.controller;

import c1020g1.social_network.model.Province;
import c1020g1.social_network.model.User;
import c1020g1.social_network.service.ProvinceService;
import c1020g1.social_network.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class SearchController {
    @Autowired
    SearchService searchService;

    @Autowired
    ProvinceService provinceService;

    /**
     * @author KienTH
     * get list user by name
     */
    @GetMapping("/searching/name-search/{name}")
    public ResponseEntity<List<User>> doNameSearch(@PathVariable("name") String name) {
        List<User> list = searchService.findAllUserByNameContain(name);
        if (list.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
            return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    /**
     * @author KienTH
     * get list user by multi param
     */
    @GetMapping("/searching/advanced-search")
    public ResponseEntity<List<User>> doAdvancedSearch(@RequestParam("name") String name,
                                       @RequestParam(value = "birthday", required = false) String birthday,
                                       @RequestParam(value = "gender", required = false) String gender,
                                       @RequestParam(value = "province", required = false) String province,
                                       @RequestParam(value = "district", required = false) String district,
                                       @RequestParam(value = "ward", required = false) String ward,
                                       @RequestParam(value = "occupation", required = false) String occupation,
                                       @RequestParam(value = "favourites", required = false) List<String> favourites) {
        Integer birthdayInt;
        if (birthday.equals("undefined") || birthday.equals("Not select")) {
            birthdayInt = null;
        } else {
            birthdayInt = Integer.parseInt(birthday);
        }

        if (province.equals("undefined") || province.equals("Not select")) {
            province = null;
        }

        if (gender.equals("undefined") || gender.equals("Not select")) {
            gender = null;
        }

        List<User> list = searchService.advancedSearch(name, birthdayInt, gender, province,
                district, ward, occupation, favourites);

        if (list.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    /**
     * @author KienTH
     * get list user recommendation
     */
    @GetMapping("/searching/recommend")
    public ResponseEntity<List<User>> doRecommendation(@RequestParam("id") Integer id) {
        User user = searchService.findById(id);
        List<String> listFavourite = searchService.getListFavourite(user.getUserId());

        List<User> list = searchService.recommendation(user.getUserId(), user.getBirthday(),
                user.getWard().getDistrict().getProvince().getProvinceId(), listFavourite);

        if (list.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    @GetMapping("/searching/province")
    public ResponseEntity<Iterable<Province>> getAllProvince() {
        Iterable<Province> list = provinceService.getAllProvince();
        return new ResponseEntity<Iterable<Province>>(list, HttpStatus.OK);
    }
}
