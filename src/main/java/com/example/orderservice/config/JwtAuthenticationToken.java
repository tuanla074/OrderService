package com.example.orderservice.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Long userId;

    public JwtAuthenticationToken(Long userId) {
        super(null);
        this.userId = userId;
        setAuthenticated(true); // Set the token as authenticated
    }

    @Override
    public Object getCredentials() {
        return null; // No credentials required for JWT
    }

    @Override
    public Object getPrincipal() {
        return userId; // The principal is the userId
    }
}
