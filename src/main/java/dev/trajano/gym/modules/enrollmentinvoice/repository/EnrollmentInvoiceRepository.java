package dev.trajano.gym.modules.enrollmentinvoice.repository;

import dev.trajano.gym.modules.enrollmentinvoice.domain.EnrollmentInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentInvoiceRepository extends JpaRepository<EnrollmentInvoice, Long> {
}
