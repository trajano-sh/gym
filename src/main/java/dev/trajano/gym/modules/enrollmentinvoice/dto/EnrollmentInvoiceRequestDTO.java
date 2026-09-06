package dev.trajano.gym.modules.enrollmentinvoice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EnrollmentInvoiceRequestDTO(
        Long enrollmentId,
        LocalDate dueDate,
        BigDecimal value
) {
}
