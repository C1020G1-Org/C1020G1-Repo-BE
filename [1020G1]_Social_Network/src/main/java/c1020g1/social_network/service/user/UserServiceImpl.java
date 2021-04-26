package c1020g1.social_network.service.user;

import c1020g1.social_network.model.User;
import c1020g1.social_network.repository.UserInfoRepository;
import c1020g1.social_network.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    /** get User by ID
     *  author: DungHA
     * */
    public User findUSerByUserId(Integer id) {
        return userInfoRepository.findUserByUserId(id);
    }
}
