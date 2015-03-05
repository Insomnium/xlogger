package net.ins.xlogger.auth;

import net.ins.xlogger.auth.validation.Password;
import net.ins.xlogger.auth.validation.PasswordConfirmation;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by insomnium on 8/8/14.
 */
@PasswordConfirmation(password = "password", confirmation = "passwordConfirmation", message = "{validation.registration.pwd.unconfirmed}")
public class RegistrationRequest implements Serializable {

    @Email(message = "{validation.registration.email}")
    private String email;

    @NotEmpty(message = "{validation.registration.nickname}")
    private String login;

    private String rules;

    @Password(message = "{validation.registration.pwd.complexity}")
    private String password;

    @Password(message = "{validation.registration.pwd.complexity}")
    private String passwordConfirmation;

    private String firstName;

    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
