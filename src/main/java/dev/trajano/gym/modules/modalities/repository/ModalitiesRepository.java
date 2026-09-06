package dev.trajano.gym.modules.modalities.repository;

import dev.trajano.gym.modules.modalities.domain.Modalities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModalitiesRepository extends JpaRepository<Modalities, Long> {
}
