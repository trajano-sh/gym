package dev.trajano.mastersys.modules.regularity.repository;

import dev.trajano.mastersys.modules.regularity.model.Regularity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegularityRepository extends JpaRepository<Regularity, Long> {
}