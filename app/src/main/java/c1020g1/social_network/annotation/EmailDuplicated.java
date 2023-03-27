package c1020g1.social_network.annotation;

import c1020g1.social_network.validator.EmailDuplicatedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailDuplicatedValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailDuplicated {
    String message() default "email_duplicated";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}