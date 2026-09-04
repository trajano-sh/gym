package dev.trajano.mastersys.repository;

import dev.trajano.mastersys.domain.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentsRepository extends JpaRepository<Enrollments, Long> {
}
