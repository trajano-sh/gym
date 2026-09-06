package dev.trajano.gym.modules.enrollmentinvoice.dto;

import dev.trajano.gym.modules.enrollmentinvoice.domain.EnrollmentInvoiceEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record EnrollmentInvoiceResponseDTO(
        Long id,
        Long enrollmentId,
        LocalDate dueData,
        BigDecimal value,
        LocalDateTime paymentDate,
        LocalDate cancellationDate,
        EnrollmentInvoiceEnum status
) {
}
