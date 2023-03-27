package c1020g1.social_network.service.account.imp;

import c1020g1.social_network.model.Account;
import c1020g1.social_network.repository.AccountRepository;
import c1020g1.social_network.sercurity.AccountPrincipleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class JwtAccountDetailService implements UserDetailsService {

    String secretPwt = "123456";

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Method: get account from database by accounName input
     * if account null throw exeption, if have account build Userdetails
     * Author: DươngLQ
     * @param accountName
     * @return
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account account = accountRepository.getAccountByName(accountName);
        if(account == null) {
            throw new UsernameNotFoundException("Account not found with account name: " + accountName);
        }
        return AccountPrincipleFactory.build(account);
    }

    /**
     * Method: change password
     * Author: DươngLQ
     * @param accountName
     */

    public void update(String accountName, String newPass) {

        accountRepository.changePassword(passwordEncoder.encode(newPass),accountName);
    }

    /**
     * Method: get account from database by account name
     * Authỏ: DươngLQ
     * @param accountName
     * @return
     */
    public Account getAccount(String accountName){
        return accountRepository.getAccountByName(accountName);
    }
}
