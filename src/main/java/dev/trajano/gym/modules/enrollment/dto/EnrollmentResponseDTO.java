package dev.trajano.gym.modules.enrollment.dto;

import dev.trajano.gym.modules.enrollment.domain.EnrollmentEnum;

import java.time.LocalDate;

public record EnrollmentResponseDTO(
        Long id,
        Long studentsId,
        LocalDate enrollmentDate,
        LocalDate dayMaturity,
        EnrollmentEnum status
) {
}
