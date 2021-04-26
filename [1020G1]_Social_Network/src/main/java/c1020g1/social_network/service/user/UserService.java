package c1020g1.social_network.service.user;

import c1020g1.social_network.model.User;

public interface UserService {
    /** get User by ID
     *  author: DungHA
     * */
    User findUSerByUserId(Integer id);

}
