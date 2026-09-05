package dev.trajano.mastersys.modules.enrollmentinvoices.repository;

import dev.trajano.mastersys.modules.enrollmentinvoices.model.EnrollmentInvoices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentInvoicesRepository extends JpaRepository<EnrollmentInvoices, Long> {
}
