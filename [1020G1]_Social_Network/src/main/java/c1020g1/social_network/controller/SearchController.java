package c1020g1.social_network.controller;

import c1020g1.social_network.model.User;
import c1020g1.social_network.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class SearchController {
    @Autowired
    SearchService searchService;

    @GetMapping("/searching/name-search/{name}")
    public List<User> doNameSearch(@PathVariable("name") String name) {
        return searchService.findAllUserByNameContain(name);
    }

    @GetMapping("/searching/advanced-search")
    public List<User> doAdvancedSearch(@RequestParam("name") String name,
                                       @RequestParam(value = "birthday", required = false) String birthday,
                                       @RequestParam(value = "gender", required = false) String gender,
                                       @RequestParam(value = "province", required = false) String province,
                                       @RequestParam(value = "district", required = false) String district,
                                       @RequestParam(value = "ward", required = false) String ward,
                                       @RequestParam(value = "occupation", required = false) String occupation,
                                       @RequestParam(value = "favourites", required = false) List<String> favourites) {
        Integer birthdayInt;
        if (birthday.equals("undefined")) {
            birthdayInt = null;
        } else {
            birthdayInt = Integer.parseInt(birthday);
        }
        return searchService.advancedSearch(name, birthdayInt, gender, province, district, ward, occupation, favourites);
    }

    @GetMapping("/searching/recommend")
    public List<User> doRecommendation(@RequestParam("id") Integer id) {
        User user = searchService.findById(id);
        List<String> list = searchService.getListFavourite(user.getUserId());
        return searchService.recommendation(user.getUserId(), user.getBirthday(),
                user.getWard().getDistrict().getProvince().getProvinceId(), list);
    }
}
