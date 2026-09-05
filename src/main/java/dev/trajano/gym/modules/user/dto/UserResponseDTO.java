package dev.trajano.gym.modules.user.dto;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String username,
        String role,
        LocalDateTime createdAt,
        LocalDateTime updateAt
) {
}
