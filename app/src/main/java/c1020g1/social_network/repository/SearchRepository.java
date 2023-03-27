package c1020g1.social_network.repository;

import c1020g1.social_network.model.Friends;
import c1020g1.social_network.model.GroupUser;
import c1020g1.social_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u " +
    "where u.userName like %:name%")
    List<User> findAllUserByNameContain(String name);

    @Query("select u from User u " +
            "join Ward w on u.ward.wardId = w.wardId " +
            "join District d on w.district.districtId = d.districtId " +
            "join Province p on d.province.provinceId = p.provinceId " +
            "join FavouriteUser f on u.userId = f.user.userId " +
            "join Favourite fv on f.favourite.favouriteId = fv.favouriteId " +
            "where (u.userName like %:name%) and " +
            "((:birthday is null) or ((function('year', u.birthday)) = :birthday)) and " +
            "(:gender is null or u.gender = :gender) and " +
            "(:province is null or u.ward.district.province.provinceName = :province) and " +
            "(:district is null or u.ward.district.districtName = :district) and " +
            "(:ward is null or u.ward.wardName = :ward) and " +
            "(:occupation is null or u.occupation = :occupation) and " +
            "(fv.favouriteName in :favourites) " +
            "group by u.userName")
    List<User> advancedSearch(String name, Integer birthday, String gender, String province, String district, String ward,
                              String occupation, List<String> favourites);

    @Query("select u from User u " +
            "join Ward w on u.ward.wardId = w.wardId " +
            "join District d on w.district.districtId = d.districtId " +
            "join Province p on d.province.provinceId = p.provinceId " +
            "where (u.userName like %:name%) and " +
            "((:birthday is null) or ((function('year', u.birthday)) = :birthday)) and " +
            "(:gender is null or u.gender = :gender) and " +
            "(:province is null or u.ward.district.province.provinceName = :province) and " +
            "(:district is null or u.ward.district.districtName = :district) and " +
            "(:ward is null or u.ward.wardName = :ward) and " +
            "(:occupation is null or u.occupation = :occupation) " +
            "group by u.userName")
    List<User> advancedSearchNoFavourites(String name, Integer birthday, String gender, String province, String district, String ward,
                              String occupation);

    @Query("select u from User u " +
            "join Ward w on u.ward.wardId = w.wardId " +
            "join District d on w.district.districtId = d.districtId " +
            "join Province p on d.province.provinceId = p.provinceId " +
            "left join FavouriteUser f on u.userId = f.user.userId " +
            "left join Favourite fv on f.favourite.favouriteId = fv.favouriteId " +
            "where (((((function('year', u.birthday)) between ((function('year', :birthday)) - 5) " +
            "and ((function('year', :birthday)) + 5))) and " +
            "(u.gender <> :gender) and " +
            "(u.ward.district.province.provinceId = :province)) or " +
            "(fv.favouriteName in :favourites)) and " +
            "((u not in (select u from User " +
            "join Friends fr on u.userId = fr.friend.userId " +
            "where fr.user.userId = :id) and u.userId <> :id )) " +
            "group by u.userName")
    List<User> recommendation(Integer id, Date birthday, String gender, Integer province, List<String> favourites);

    @Query("select u from User u " +
            "join Ward w on u.ward.wardId = w.wardId " +
            "join District d on w.district.districtId = d.districtId " +
            "join Province p on d.province.provinceId = p.provinceId " +
            "where (((function('year', u.birthday)) between ((function('year', :birthday)) - 5) " +
            "and ((function('year', :birthday)) + 5))) and " +
            "(u.gender <> :gender) and " +
            "(u.ward.district.province.provinceId = :province) and " +
            "((u not in (select u from User " +
            "join Friends fr on u.userId = fr.friend.userId " +
            "where fr.user.userId = :id) and u.userId <> :id )) " +
            "group by u.userName")
    List<User> recommendationNoFavourite(Integer id, Date birthday, String gender, Integer province);

    @Query("select f from Friends f " +
            "where f.user.userId = :userId and " +
            "f.friend.userId = :friendId")
    Friends findFriends(Integer userId, Integer friendId);

    @Query("select g from GroupUser g " +
            "where g.user.userId = :userId and " +
            "g.groupSocial.groupId = :groupId")
    GroupUser findGroupUser(Integer userId, Integer groupId);
}
