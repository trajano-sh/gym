package dev.trajano.gym.modules.enrollmentinvoice.mapper;

import dev.trajano.gym.modules.enrollmentinvoice.dto.EnrollmentInvoiceRequestDTO;
import dev.trajano.gym.modules.enrollmentinvoice.dto.EnrollmentInvoiceResponseDTO;
import dev.trajano.gym.modules.enrollmentinvoice.domain.EnrollmentInvoice;
import dev.trajano.gym.modules.enrollment.domain.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentInvoiceMapper {
    public EnrollmentInvoice toEntity(EnrollmentInvoiceRequestDTO requestDTO, Enrollment enrollments) {
        EnrollmentInvoice enrollmentInvoice = new EnrollmentInvoice();
        enrollmentInvoice.setEnrollmentId(enrollments);
        enrollmentInvoice.setValue(requestDTO.value());
        enrollmentInvoice.setDueDate(requestDTO.dueDate());
        return enrollmentInvoice;
    }

    public EnrollmentInvoiceResponseDTO fromEntity(EnrollmentInvoice enrollmentInvoice) {
        return new EnrollmentInvoiceResponseDTO(
                enrollmentInvoice.getId(),
                enrollmentInvoice.getEnrollmentId().getId(),
                enrollmentInvoice.getDueDate(),
                enrollmentInvoice.getValue(),
                enrollmentInvoice.getPaymentDate(),
                enrollmentInvoice.getCancellationDate(),
                enrollmentInvoice.getStatus());
    }
}
