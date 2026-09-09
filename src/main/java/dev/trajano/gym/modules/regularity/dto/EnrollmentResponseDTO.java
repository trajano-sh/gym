package dev.trajano.gym.modules.regularity.dto;

import java.time.LocalDateTime;

public record EnrollmentResponseDTO(
        Integer position,
        Long id,
        String name,
        LocalDateTime createdAt,
        Integer quantity
) {
}
