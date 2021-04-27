package c1020g1.social_network.service;

import c1020g1.social_network.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountService {
    void changePassword(Account account, String oldPassword, String newPassword, String confirmPassword);

    Account findAccountById(String accountName);

    boolean checkChangePassword(Account account,String oldPassword, String newPassword, String confirmPassword);
}
