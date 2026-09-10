package dev.trajano.gym.modules.enrollment.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EnrollmentRequestDTO(
        @NotNull Long studentId,
        @NotNull LocalDate dayMaturity
) {
}
