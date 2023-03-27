package c1020g1.social_network.sercurity;

import c1020g1.social_network.model.Account;

import java.util.ArrayList;

public class AccountPrincipleFactory {
    public static AccountPrincipal build(Account account){
        return new AccountPrincipal(account.getAccountName(),account.getPassword(),new ArrayList<>());
    }
}
