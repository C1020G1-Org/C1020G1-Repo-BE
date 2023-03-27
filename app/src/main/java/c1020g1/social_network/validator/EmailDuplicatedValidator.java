package c1020g1.social_network.validator;

import c1020g1.social_network.annotation.EmailDuplicated;
import c1020g1.social_network.model.User;
import c1020g1.social_network.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailDuplicatedValidator implements ConstraintValidator<EmailDuplicated, User> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(EmailDuplicated constraintAnnotation) {
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        User databaseUser = userService.getUserByEmail(user.getEmail());
        return databaseUser == null || databaseUser.getUserId().equals(user.getUserId());
    }
}
