package dev.trajano.gym.modules.modality.repository;

import dev.trajano.gym.modules.modality.domain.Modalities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModalitiesRepository extends JpaRepository<Modalities, Long> {
}
