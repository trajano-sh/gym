package dev.trajano.gym.modules.enrollments.repository;

import dev.trajano.gym.modules.enrollments.model.Enrollments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentsRepository extends JpaRepository<Enrollments, Long> {
}
