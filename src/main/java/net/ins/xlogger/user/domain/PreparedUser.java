package net.ins.xlogger.user.domain;

import java.util.Date;
import java.util.Set;

/**
 * Created by ins on 3/30/15.
 */
public class PreparedUser {
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private boolean blocked;
    private boolean activated;
    private Date lastLogin;
    private Set<Role> roleSysNames;

    public PreparedUser() {
    }

    public PreparedUser(String login, String email, String firstName, String lastName, String avatarUrl, boolean blocked,
                        boolean activated, Date lastLogin, Set<Role> roleSysNames) {
        this.login = login;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
        this.blocked = blocked;
        this.activated = activated;
        this.lastLogin = lastLogin;
        this.roleSysNames = roleSysNames;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set<Role> getRoleSysNames() {
        return roleSysNames;
    }

    public void setRoleSysNames(Set<Role> roleSysNames) {
        this.roleSysNames = roleSysNames;
    }
}