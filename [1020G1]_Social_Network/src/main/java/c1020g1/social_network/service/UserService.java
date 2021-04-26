package c1020g1.social_network.service;

import c1020g1.social_network.model.User;



public interface UserService {
    /** get User by ID
     *  author: DungHA
     * */
    User findUSerByUserId(Integer id);
}
