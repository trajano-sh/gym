package dev.trajano.gym.modules.enrollmentinvoice.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EnrollmentInvoiceRequestDTO(
        @NotNull Long enrollmentId,
        LocalDate dueDate,
        BigDecimal value
) {
}
