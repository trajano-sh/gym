package dev.trajano.gym.modules.enrollmentinvoices.mapper;

import dev.trajano.gym.modules.enrollmentinvoices.dto.EnrollmentInvoicesRequestDTO;
import dev.trajano.gym.modules.enrollmentinvoices.dto.EnrollmentInvoicesResponseDTO;
import dev.trajano.gym.modules.enrollmentinvoices.model.EnrollmentInvoices;
import dev.trajano.gym.modules.enrollments.model.Enrollments;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentInvoicesMapper {
    public EnrollmentInvoices toEntity(EnrollmentInvoicesRequestDTO requestDTO, Enrollments enrollments) {
        EnrollmentInvoices enrollmentInvoices = new EnrollmentInvoices();
        enrollmentInvoices.setEnrollmentId(enrollments);
        enrollmentInvoices.setValue(requestDTO.value());
        enrollmentInvoices.setDueDate(requestDTO.dueDate());
        return enrollmentInvoices;
    }

    public EnrollmentInvoicesResponseDTO fromEntity(EnrollmentInvoices enrollmentInvoices) {
        return new EnrollmentInvoicesResponseDTO(
                enrollmentInvoices.getId(),
                enrollmentInvoices.getEnrollmentId().getId(),
                enrollmentInvoices.getDueDate(),
                enrollmentInvoices.getValue(),
                enrollmentInvoices.getPaymentDate(),
                enrollmentInvoices.getCancellationDate(),
                enrollmentInvoices.getStatus());
    }
}
