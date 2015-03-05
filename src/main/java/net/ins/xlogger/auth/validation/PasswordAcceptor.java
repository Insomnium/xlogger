package net.ins.xlogger.auth.validation;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by insomnium on 8/8/14.
 */
public class PasswordAcceptor implements ConstraintValidator<PasswordConfirmation, Object> {

    private String passwordFieldName;
    private String confirmationPasswordFieldName;

    @Override
    public void initialize(PasswordConfirmation passwordConfirmation) {
        passwordFieldName = passwordConfirmation.password();
        confirmationPasswordFieldName = passwordConfirmation.confirmation();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        String password = "";
        String confirmation = "";
        try {
            password = BeanUtils.getProperty(obj, passwordFieldName);
            confirmation = BeanUtils.getProperty(obj, confirmationPasswordFieldName);
        } catch (Exception e) {
            password = "";
            confirmation = "";
        }
        return StringUtils.equals(password, confirmation);
    }
}
