package c1020g1.social_network.service.user;

import c1020g1.social_network.model.User;
import c1020g1.social_network.model.dto.UserCreateDTO;

import java.util.List;

public interface UserService {

    List<User> inviteFriendList(int groupId, int userId);

    List<User> inviteFriendsOfFriendsList(int groupId, int userId);

    //HanTH
    void updateStatus(Integer userId, Integer statusId);

    //HanTH
    void updateAvatar(Integer userId, String image, String imageName);

    //HanTH
    void updateBackground(Integer userId, String background, String imageName);


    //PhucPT
    User createUser(UserCreateDTO userCreateDTO);

    //PhucPT
    User getUserById(Integer id);

    //Duong
    User getUserByEmail(String email);

    //ThinhTHB
    void save(User user);
}
