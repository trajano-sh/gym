package dev.trajano.gym.modules.regularity.repository;

import dev.trajano.gym.modules.regularity.model.Regularity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegularityRepository extends JpaRepository<Regularity, Long> {
}