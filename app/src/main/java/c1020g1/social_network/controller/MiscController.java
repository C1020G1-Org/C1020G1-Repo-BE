package c1020g1.social_network.controller;

import c1020g1.social_network.model.District;
import c1020g1.social_network.model.Favourite;
import c1020g1.social_network.model.Province;
import c1020g1.social_network.model.Ward;
import c1020g1.social_network.service.district.DistrictService;
import c1020g1.social_network.service.group.FavouriteService;
import c1020g1.social_network.service.province.ProvinceService;
import c1020g1.social_network.service.group.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/misc")
@CrossOrigin("http://localhost:4200")
public class MiscController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private WardService wardService;

    @Autowired
    private FavouriteService favouriteService;

    /**
     * author: PhucPT
     * method: get all provinces in database and attach an JSON in HTTP response
     * @return
     */
    @GetMapping("/province")
    public ResponseEntity<Iterable<Province>> listProvinces() {
        Iterable<Province> provinces = provinceService.getAllProvince();
        return new ResponseEntity<>(provinces, HttpStatus.OK);
    }

    /**
     * author: PhucPT
     * method: get all favourites in database as JSON in HTTP response
     * @return
     */
    @GetMapping("/favourites")
    public ResponseEntity<Iterable<Favourite>> listFavourites() {
        Iterable<Favourite> favourites = favouriteService.getAllFavourite();
        return new ResponseEntity<>(favourites, HttpStatus.OK);
    }

    /**
     * author: PhucPT
     * method: get all districts in database by a province id as JSON in HTTP response
     * @param provinceId
     * @return
     */
    @GetMapping("/district/{id}")
    public ResponseEntity<Iterable<District>> listDistrictByProvinceId(@PathVariable("id") int provinceId) {
        Iterable<District> districts = districtService.getDistrictByProvinceId(provinceId);
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    /**
     * author: PhucPT
     * method: get all wards in database by a district id as JSON in HTTP response
     * @param districtId
     * @return
     */
    @GetMapping("/ward/{id}")
    public ResponseEntity<Iterable<Ward>> listWardByDistrictId(@PathVariable("id") int districtId) {
        Iterable<Ward> wards = wardService.getWardByDistrictId(districtId);
        return new ResponseEntity<>(wards, HttpStatus.OK);
    }
}
