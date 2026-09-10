package dev.trajano.gym.modules.enrollment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record EnrollmentRequestDTO(
        @NotNull Long studentId,
        @NotNull @PastOrPresent LocalDate dayMaturity
) {
}
