package dev.trajano.gym.modules.enrollments.dto;

import java.time.LocalDate;

public record EnrollmentsRequestDTO(
        Long studentId,
        LocalDate dayMaturity
) {
}
