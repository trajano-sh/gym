package dev.trajano.gym.modules.enrollmentsmodality.dto;

import java.time.LocalDate;

public record EnrollmentsModalitiesResponseDTO(
        Long id,
        Long enrollmentId,
        Long modalityId,
        Long graduationId,
        Long planId,
        LocalDate startDate,
        LocalDate endDate
) {
}
