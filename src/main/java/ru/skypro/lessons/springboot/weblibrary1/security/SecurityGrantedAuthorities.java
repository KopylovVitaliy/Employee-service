package ru.skypro.lessons.springboot.weblibrary1.security;

import org.springframework.security.core.GrantedAuthority;

public class SecurityGrantedAuthorities implements GrantedAuthority {
    private String role;

    public SecurityGrantedAuthorities(UserRole userRole) {
        this.role = userRole.getRoles();
    }

    @Override
    public String getAuthority() {
        return null;
    }
}