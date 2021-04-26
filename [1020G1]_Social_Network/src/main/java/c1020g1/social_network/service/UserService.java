package c1020g1.social_network.service;

import c1020g1.social_network.model.User;
import c1020g1.social_network.model.dto.UserCreateDTO;

public interface UserService {

    User createUser(UserCreateDTO userCreateDTO);

    User getUserById(Integer id);

    User getUserByEmail(String email);
}
