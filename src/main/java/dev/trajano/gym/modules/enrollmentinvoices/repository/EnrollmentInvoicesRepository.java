package dev.trajano.gym.modules.enrollmentinvoices.repository;

import dev.trajano.gym.modules.enrollmentinvoices.model.EnrollmentInvoices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentInvoicesRepository extends JpaRepository<EnrollmentInvoices, Long> {
}
