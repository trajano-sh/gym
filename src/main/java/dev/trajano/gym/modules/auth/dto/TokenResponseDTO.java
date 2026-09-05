package dev.trajano.gym.modules.auth.dto;

public record TokenResponseDTO(
        String token,
        String typeToken,
        Long expirationAt
) {
}
