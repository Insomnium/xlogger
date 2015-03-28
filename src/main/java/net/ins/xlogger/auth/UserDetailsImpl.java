package net.ins.xlogger.auth;

import net.ins.xlogger.user.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ins on 3/3/15.
 */
public class UserDetailsImpl implements UserDetails {

    private User user;
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    public UserDetailsImpl(User user) {
        this.user = user;
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            authorities.addAll(user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRoleSysName()))
                    .collect(Collectors.toList()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.isBlocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isActivated();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }


}
