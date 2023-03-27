package c1020g1.social_network.service.account.imp;

import c1020g1.social_network.model.Account;
import c1020g1.social_network.repository.AccountRepository;
import c1020g1.social_network.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * method: change password of user through account.
     * author: HanTH.
     *
     * @param account
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    @Override
    public void changePassword(Account account, String oldPassword, String newPassword, String confirmPassword) {
        if (checkChangePassword(account,oldPassword,newPassword,confirmPassword)) {
            accountRepository.changePassword(passwordEncoder.encode( newPassword ), account.getAccountName());
        }
    }

    /**
     * method: find account by id of account.
     * author: HanTH
     *
     * @param accountName
     * @return
     */
    @Override
    public Account findAccountByName(String accountName) {
        return accountRepository.getAccountByName( accountName );
    }

    /**
     * method: check old password of user valid to change password.
     * author: HanTH
     *
     * @param account
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @Override
    public boolean checkChangePassword(Account account, String oldPassword, String newPassword, String confirmPassword) {
        if (passwordEncoder.matches( oldPassword, account.getPassword())) {
            if (!oldPassword.equals(newPassword) && newPassword.equals(confirmPassword)){
                return true;
            };
        }
        return false;
    }
}
