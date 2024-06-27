package com.nicolascruz.push.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AppSecurity {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }

    public Long getUserId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        Object userId = jwt.getClaim("user_id");

        if (userId == null) {
            return null;
        }

        return Long.valueOf(userId.toString());
    }

    public boolean authenticatedUserEquals(Long userId) {
        return getUserId() != null && userId != null
                && getUserId().equals(userId);
    }

    public boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean hasWriteScope() {
        return hasAuthority("SCOPE_WRITE");
    }

    public boolean hasReadScope() {
        return hasAuthority("SCOPE_READ");
    }
}
