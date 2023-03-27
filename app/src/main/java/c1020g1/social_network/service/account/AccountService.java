package c1020g1.social_network.service.account;

import c1020g1.social_network.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountService {
    void changePassword(Account account, String oldPassword, String newPassword, String confirmPassword);

    Account findAccountByName(String accountName);

    boolean checkChangePassword(Account account,String oldPassword, String newPassword, String confirmPassword);
}
