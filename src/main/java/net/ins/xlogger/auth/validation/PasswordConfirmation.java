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
@Constraint(validatedBy = PasswordAcceptor.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConfirmation {
    String password();
    String confirmation();
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
