package net.ins.xlogger.auth.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by insomnium on 8/8/14.
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private int complexityThreshold;

    @Override
    public void initialize(Password password) {
        complexityThreshold = password.requiredComplexity();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isEmpty(s) || s.length() < 7) {
            return false;
        }
        int complexity = 0;
        complexity += s.matches(".*[0-9]+.*") ? 1 : 0;
        complexity += s.matches(".*[a-z]+.*") ? 1 : 0;
        complexity += s.matches(".*[A-Z]+.*") ? 1 : 0;
        return complexity > 1;
    }
}
