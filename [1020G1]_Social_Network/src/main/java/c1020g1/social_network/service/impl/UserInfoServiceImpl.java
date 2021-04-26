package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.User;
import c1020g1.social_network.repository.UserInfoRepository;
import c1020g1.social_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserInfoServiceImpl implements UserService {
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
