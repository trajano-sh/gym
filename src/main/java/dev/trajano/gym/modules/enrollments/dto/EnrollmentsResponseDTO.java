package dev.trajano.gym.modules.enrollments.dto;

import dev.trajano.gym.modules.enrollments.model.EnrollmentsEnum;

import java.time.LocalDate;

public record EnrollmentsResponseDTO(
        Long id,
        Long studentId,
        LocalDate enrollmentDate,
        LocalDate dayMaturity,
        EnrollmentsEnum status
) {
}
