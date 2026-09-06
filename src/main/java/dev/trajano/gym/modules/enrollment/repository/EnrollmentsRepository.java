package dev.trajano.gym.modules.enrollment.repository;

import dev.trajano.gym.modules.enrollment.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentsRepository extends JpaRepository<Enrollment, Long> {
}
