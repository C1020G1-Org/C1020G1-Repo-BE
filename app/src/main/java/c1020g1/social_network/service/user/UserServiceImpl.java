
package c1020g1.social_network.service.user;


import c1020g1.social_network.model.Favourite;
import c1020g1.social_network.model.Status;
import c1020g1.social_network.model.User;
import c1020g1.social_network.model.Account;

import c1020g1.social_network.model.dto.UserCreateDTO;
import c1020g1.social_network.repository.FavouriteUserRepository;
import c1020g1.social_network.repository.UserRepository;

import c1020g1.social_network.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FavouriteUserRepository favouriteUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * author: PhucPT
     * method: transaction invoke account repository, user repository, favourite repository to create account,
     * user and favourites in database. Return user object has been created
     *
     * @param userCreateDTO
     * @return
     */
    @Override
    @Transactional
    public User createUser(UserCreateDTO userCreateDTO) {
        Account account = userCreateDTO.getAccount();
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        User user = userCreateDTO.getUser();
        Favourite[] favourites = userCreateDTO.getFavourites();

        accountRepository.createAccount(account);
        Account newAccount = accountRepository.getAccountByName(account.getAccountName());

        user.setAccount(newAccount);
        user.setStatus(new Status(1, "online"));
        userRepository.createUser(user);

        User newUser = userRepository.getUserByAccountId(newAccount.getAccountId());

        for (Favourite favourite : favourites) {
            favouriteUserRepository.createFavouriteUser(favourite, newUser);
        }

        return newUser;
    }

    /**
     * author: PhucPT
     * method: return user in database by id
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }


    /**
     * method: update status of user.
     * author: HanTH.
     *
     * @param userId
     * @param statusId
     */
    @Override
    public void updateStatus(Integer userId, Integer statusId) {
        userRepository.updateStatus(userId, statusId);
    }

    /**
     * method: update avatar of user.
     * author: HanTH
     *
     * @param userId
     * @param image
     * @param imageName
     */
    @Override
    public void updateAvatar(Integer userId, String image, String imageName) {
        if (imageName.equals("png") || imageName.equals("jpg")) {
            userRepository.updateAvatar(userId, image);
        }
    }

    /**
     * method: update background of user.
     *
     * @param userId
     * @param background
     * @param imageName
     */
    @Override
    public void updateBackground(Integer userId, String background, String imageName) {
        if (imageName.equals("png") || imageName.equals("jpg")) {
            userRepository.updateBackground(userId, background);
        }
    }


    //dương
    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public List<User> inviteFriendList(int groupId, int userId) {
        return userRepository.inviteFriends(groupId, userId);
    }

    @Override
    public List<User> inviteFriendsOfFriendsList(int groupId, int userId) {
        return userRepository.inviteFriendsOfFriends(groupId, userId);
    }


    //ThinhTHB
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

}
