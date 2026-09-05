package dev.trajano.mastersys.modules.plans.repository;

import dev.trajano.mastersys.modules.plans.model.Plans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlansRepository extends JpaRepository<Plans, Long> {
}
