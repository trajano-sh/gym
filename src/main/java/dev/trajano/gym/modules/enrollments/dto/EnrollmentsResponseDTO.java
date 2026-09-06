package dev.trajano.gym.modules.enrollments.dto;

import dev.trajano.gym.modules.enrollments.domain.EnrollmentsEnum;

import java.time.LocalDate;

public record EnrollmentsResponseDTO(
        Long id,
        Long studentsId,
        LocalDate enrollmentDate,
        LocalDate dayMaturity,
        EnrollmentsEnum status
) {
}
