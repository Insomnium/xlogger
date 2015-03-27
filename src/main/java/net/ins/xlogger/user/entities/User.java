package net.ins.xlogger.user.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by ins on 3/1/15.
 */
@Entity
@Table(name = "USERS")
@NamedQueries({
        @NamedQuery(name = "user.selectByLogin", query = "select u from User u where u.login = :login", cacheable = true, cacheRegion = "Users"),
        @NamedQuery(name = "user.countByEmail", query = "select count(1) from User u where u.email = :email")
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "Users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false, insertable = true, unique = true)
    private Long id;

    @Column(name = "first_name", nullable = true, updatable = true, insertable = true)
    private String firstName;

    @Column(name = "last_name", nullable = true, updatable = true, insertable = true)
    private String lastName;

    @Column(name = "email", nullable = false, updatable = true, insertable = true, unique = true)
    private String email;

    @Column(name = "password", nullable = false, updatable = true, insertable = true, unique = false)
    private String password;

    @Column(name = "avatar", nullable = true, updatable = true, insertable = true, unique = true)
    private String avatarUrl;

    @Column(name = "login", nullable = false, updatable = false, insertable = true, unique = true)
    private String login;

    @Column(name = "blocked", nullable = false, updatable = true, insertable = true)
    private boolean blocked;

    @Column(name = "info", nullable = true, updatable = true, insertable = true)
    private String info;

    @Column(name = "lastlogin", nullable = true, updatable = true, insertable = true)
    private Date lastLogin;

    @Column(name = "activated", nullable = false, updatable = true, insertable = true)
    private boolean activated;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
    @JoinTable(name = "user_roles",
                joinColumns = { @JoinColumn(name = "user_id")},
                inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles;

    public User() {
    }

//    public User(ResultSet rs) throws SQLException {
//        this.id = rs.getLong("user_id");
//        this.firstName = rs.getString("first_name");
//        this.lastName = rs.getString("last_name");
//        this.email = rs.getString("email");
//        this.password = rs.getString("password");
//        this.avatarUrl = rs.getString("avatar");
//        this.login = rs.getString("login");
//        this.blocked = rs.getBoolean("blocked");
//        this.info = rs.getString("info");
//        this.lastLogin = rs.getTimestamp("lastlogin");
//    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (activated != user.activated) return false;
        if (blocked != user.blocked) return false;
        if (avatarUrl != null ? !avatarUrl.equals(user.avatarUrl) : user.avatarUrl != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (info != null ? !info.equals(user.info) : user.info != null) return false;
        if (lastLogin != null ? !lastLogin.equals(user.lastLogin) : user.lastLogin != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (roles != null ? !roles.equals(user.roles) : user.roles != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (blocked ? 1 : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        result = 31 * result + (activated ? 1 : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", login='" + login + '\'' +
                ", blocked=" + blocked +
                ", info='" + info + '\'' +
                ", lastLogin=" + lastLogin +
                ", activated=" + activated +
                ", roles=" + roles +
                '}';
    }
}
