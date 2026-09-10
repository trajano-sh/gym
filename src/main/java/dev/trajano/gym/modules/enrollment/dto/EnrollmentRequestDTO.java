package dev.trajano.gym.modules.enrollment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record EnrollmentRequestDTO(
        @NotNull @Positive Long studentId,
        @NotNull @PastOrPresent LocalDate dayMaturity
) {
}
