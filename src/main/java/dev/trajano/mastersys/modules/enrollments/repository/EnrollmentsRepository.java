package dev.trajano.mastersys.modules.enrollments.repository;

import dev.trajano.mastersys.modules.enrollments.model.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentsRepository extends JpaRepository<Enrollments, Long> {
}
