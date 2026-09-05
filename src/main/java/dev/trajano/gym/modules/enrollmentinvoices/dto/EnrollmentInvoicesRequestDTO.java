package dev.trajano.gym.modules.enrollmentinvoices.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EnrollmentInvoicesRequestDTO(
        Long enrollmentId,
        LocalDate dueDate,
        BigDecimal value
) {
}
