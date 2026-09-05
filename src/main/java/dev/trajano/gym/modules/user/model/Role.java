package dev.trajano.gym.modules.user.model;

import jakarta.annotation.Nullable;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, BASIC;

    @Override
    public @Nullable String getAuthority() {
        return "ROLE_" + this.name();
    }
}
