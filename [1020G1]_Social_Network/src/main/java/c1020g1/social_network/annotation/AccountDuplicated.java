package c1020g1.social_network.annotation;

import c1020g1.social_network.validator.AccountDuplicatedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AccountDuplicatedValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountDuplicated {
    String message() default "duplicated";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

