package c1020g1.social_network.service;

import c1020g1.social_network.model.Favourite;
import c1020g1.social_network.model.Status;
import c1020g1.social_network.model.User;
import c1020g1.social_network.model.Account;
import c1020g1.social_network.model.dto.UserCreateDTO;
import c1020g1.social_network.repository.FavouriteUserRepository;
import c1020g1.social_network.repository.UserRepository;
import c1020g1.social_network.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FavouriteUserRepository favouriteUserRepository;

    @Override
    @Transactional
    public User createUser(UserCreateDTO userCreateDTO) {
        Account account = userCreateDTO.getAccount();
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

    @Override
    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }
//dương
    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

}
