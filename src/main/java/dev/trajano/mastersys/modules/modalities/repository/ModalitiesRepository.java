package dev.trajano.mastersys.modules.modalities.repository;

import dev.trajano.mastersys.modules.modalities.model.Modalities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModalitiesRepository extends JpaRepository<Modalities, Long> {
}
