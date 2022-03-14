package de.reutow.phonebook.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

    String message() default "invalid email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };
}
