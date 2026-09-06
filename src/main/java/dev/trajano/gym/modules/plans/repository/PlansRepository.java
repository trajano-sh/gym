package dev.trajano.gym.modules.plans.repository;

import dev.trajano.gym.modules.plans.domain.Plans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlansRepository extends JpaRepository<Plans, Long> {
}
