package dev.trajano.gym.modules.enrollment.dto;

import java.time.LocalDate;

public record EnrollmentRequestDTO(
        Long studentId,
        LocalDate dayMaturity
) {
}
