package dev.trajano.gym.modules.enrollmentinvoices.repository;

import dev.trajano.gym.modules.enrollmentinvoices.domain.EnrollmentInvoices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentInvoicesRepository extends JpaRepository<EnrollmentInvoices, Long> {
}
