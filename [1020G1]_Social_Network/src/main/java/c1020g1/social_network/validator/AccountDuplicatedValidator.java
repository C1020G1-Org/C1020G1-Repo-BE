package c1020g1.social_network.validator;

import c1020g1.social_network.annotation.AccountDuplicated;
import c1020g1.social_network.model.Account;
import c1020g1.social_network.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountDuplicatedValidator implements ConstraintValidator<AccountDuplicated, Account> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void initialize(AccountDuplicated constraintAnnotation) {

    }

    @Override
    public boolean isValid(Account account, ConstraintValidatorContext context) {
        Account databaseAccount = accountRepository.getAccountByName(account.getAccountName());
        return databaseAccount == null || databaseAccount.getAccountId().equals(account.getAccountId());
    }
}
