package dev.trajano.gym.modules.enrollmentinvoices.dto;

import dev.trajano.gym.modules.enrollmentinvoices.domain.EnrollmentInvoicesEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record EnrollmentInvoicesResponseDTO(
        Long id,
        Long enrollmentId,
        LocalDate dueData,
        BigDecimal value,
        LocalDateTime paymentDate,
        LocalDate cancellationDate,
        EnrollmentInvoicesEnum status
) {
}
