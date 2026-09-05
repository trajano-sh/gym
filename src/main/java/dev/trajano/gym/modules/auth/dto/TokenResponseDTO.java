package dev.trajano.gym.modules.auth.dto;

public record TokenResponseDTO(
        String typeToken,
        String token,
        Long expirationAt
) {
}
