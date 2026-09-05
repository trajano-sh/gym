package dev.trajano.gym.modules.auth.dto;

public record AuthLoginRequestDTO(
        String username,
        String password
) {
}
