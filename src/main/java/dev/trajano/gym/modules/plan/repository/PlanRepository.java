package dev.trajano.gym.modules.plan.repository;

import dev.trajano.gym.modules.plan.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
