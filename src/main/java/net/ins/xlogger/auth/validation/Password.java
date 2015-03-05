package net.ins.xlogger.auth.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by insomnium on 8/8/14.
 */
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    int requiredComplexity() default 2;
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
