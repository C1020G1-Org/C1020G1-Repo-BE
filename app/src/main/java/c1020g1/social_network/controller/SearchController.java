package c1020g1.social_network.controller;

import c1020g1.social_network.model.*;
import c1020g1.social_network.model.dto.GroupStatusDTO;
import c1020g1.social_network.model.dto.UserFavouritesStatusDTO;
import c1020g1.social_network.service.province.ProvinceService;
import c1020g1.social_network.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @GetMapping("/searching/name-search/")
    public ResponseEntity<List<UserFavouritesStatusDTO>> doNameSearch(@RequestParam("name") String name,
                                                                      @RequestParam("userId") Integer userId) {
        List<User> list = searchService.findAllUserByNameContain(name);
        List<UserFavouritesStatusDTO> listUserFavouriteStatusDTOS = new ArrayList<>();

        for(User userRecommend: list){
            UserFavouritesStatusDTO userFavouritesStatusDTO = new UserFavouritesStatusDTO();
            userFavouritesStatusDTO.setUser(userRecommend);
            userFavouritesStatusDTO.setFavourites(searchService.getListFavourite(userRecommend.getUserId()));
            userFavouritesStatusDTO.setFriends(searchService.findFriends(userId, userRecommend.getUserId()));
            listUserFavouriteStatusDTOS.add(userFavouritesStatusDTO);
        }

        if (list.isEmpty()){
            return new ResponseEntity<List<UserFavouritesStatusDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserFavouritesStatusDTO>>(listUserFavouriteStatusDTOS, HttpStatus.OK);
    }

    /**
     * @author KienTH
     * get list user by multi param
     */
    @GetMapping("/searching/advanced-search")
    public ResponseEntity<List<UserFavouritesStatusDTO>> doAdvancedSearch(@RequestParam("name") String name,
                                                                          @RequestParam(value = "birthday", required = false) String birthday,
                                                                          @RequestParam(value = "gender", required = false) String gender,
                                                                          @RequestParam(value = "province", required = false) String province,
                                                                          @RequestParam(value = "district", required = false) String district,
                                                                          @RequestParam(value = "ward", required = false) String ward,
                                                                          @RequestParam(value = "occupation", required = false) String occupation,
                                                                          @RequestParam(value = "favourites", required = false) List<String> favourites,
                                                                          @RequestParam("userId") Integer userId) {
        Integer birthdayInt;
        if (birthday.equals("undefined") || birthday.equals("Not select")) {
            birthdayInt = null;
        } else {
            birthdayInt = Integer.parseInt(birthday);
        }

        if (province.equals("undefined") || province.equals("Not select")) {
            province = null;
        }

        if (name.equals("undefined")) {
            name = "";
        }

        if (gender.equals("undefined") || gender.equals("Not select")) {
            gender = null;
        }

        if (occupation.equals("undefined") || occupation.equals("")) {
            occupation = null;
        }

        List<User> list = searchService.advancedSearch(name, birthdayInt, gender, province,
                district, ward, occupation, favourites);

        List<UserFavouritesStatusDTO> listUserFavouriteStatusDTOS = new ArrayList<>();

        for(User userRecommend: list){
            UserFavouritesStatusDTO userFavouritesStatusDTO = new UserFavouritesStatusDTO();
            userFavouritesStatusDTO.setUser(userRecommend);
            userFavouritesStatusDTO.setFavourites(searchService.getListFavourite(userRecommend.getUserId()));
            userFavouritesStatusDTO.setFriends(searchService.findFriends(userId, userRecommend.getUserId()));
            listUserFavouriteStatusDTOS.add(userFavouritesStatusDTO);
        }

        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listUserFavouriteStatusDTOS, HttpStatus.OK);
    }

    /**
     * @author KienTH
     * get list user recommendation
     */
    @GetMapping("/searching/recommend")
    public ResponseEntity<List<UserFavouritesStatusDTO>> doRecommendation(@RequestParam("id") Integer id) {
        User user = searchService.findById(id);
        List<String> listFavourite = searchService.getListFavourite(user.getUserId());

        List<User> list = searchService.recommendation(user.getUserId(), user.getBirthday(), user.getGender(),
                user.getWard().getDistrict().getProvince().getProvinceId(), listFavourite);

        List<UserFavouritesStatusDTO> listUserFavouriteStatusDTOS = new ArrayList<>();

        for(User userRecommend: list){
            UserFavouritesStatusDTO userFavouritesStatusDTO = new UserFavouritesStatusDTO();
            userFavouritesStatusDTO.setUser(userRecommend);
            userFavouritesStatusDTO.setFavourites(searchService.getListFavourite(userRecommend.getUserId()));
            listUserFavouriteStatusDTOS.add(userFavouritesStatusDTO);
        }

        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listUserFavouriteStatusDTOS, HttpStatus.OK);
    }

    /**
     * @author KienTH
     * get list province
     */
    @GetMapping("/searching/province")
    public ResponseEntity<Iterable<Province>> getAllProvince() {
        Iterable<Province> list = provinceService.getAllProvince();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * @author KienTH
     * get list group by name
     */
    @GetMapping("/searching/group/")
    public ResponseEntity<List<GroupStatusDTO>> getAllGroup(@RequestParam("name") String name,
                                                               @RequestParam("userId") Integer userId) {
        List<GroupSocial> list = searchService.findAllGroupByName(name);

        List<GroupStatusDTO> listGroupStatusDTO = new ArrayList<>();

        for(GroupSocial groupSocial: list){
            GroupStatusDTO groupStatusDTO = new GroupStatusDTO();
            groupStatusDTO.setGroupSocial(groupSocial);
            groupStatusDTO.setGroupUser(searchService.findGroupUser(userId, groupSocial.getGroupId()));
            listGroupStatusDTO.add(groupStatusDTO);
        }
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listGroupStatusDTO, HttpStatus.OK);
    }


}
