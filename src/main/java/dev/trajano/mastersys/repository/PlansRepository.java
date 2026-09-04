package dev.trajano.mastersys.repository;

import dev.trajano.mastersys.domain.Plans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlansRepository extends JpaRepository<Plans, Long> {
}
