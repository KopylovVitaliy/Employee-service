package ru.skypro.lessons.springboot.weblibrary1.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUserPrincipal implements UserDetails {
    private AuthUser user;
    private List<SecurityGrantedAuthorities> authoritiesList;
    public SecurityUserPrincipal(AuthUser user) {
        this.user = user;
        this.authoritiesList = user.getUserRoles().stream()
                .map(SecurityGrantedAuthorities::new)
                .toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(authoritiesList);
    }
    @Override
    public String getPassword() {
        return user.getUsername();
    }

    @Override
    public String getUsername() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
