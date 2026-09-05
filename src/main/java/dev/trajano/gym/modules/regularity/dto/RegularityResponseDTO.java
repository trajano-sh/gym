package dev.trajano.gym.modules.regularity.dto;

import java.time.LocalDateTime;

public record RegularityResponseDTO(
        Long id,
        Long enrollmentsId,
        LocalDateTime entryDate,
        LocalDateTime exitDate
) {
}
