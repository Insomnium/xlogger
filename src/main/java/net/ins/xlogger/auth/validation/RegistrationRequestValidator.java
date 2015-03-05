package net.ins.xlogger.auth.validation;

import net.ins.xlogger.auth.RegistrationRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by insomnium on 8/8/14.
 */
public class RegistrationRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        RegistrationRequest request = (RegistrationRequest) obj;

        if (StringUtils.isEmpty(request.getLogin())) {
            errors.reject("login", null, "Nickname can not be empty");
        }
    }
}
