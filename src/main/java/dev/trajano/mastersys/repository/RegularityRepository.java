package dev.trajano.mastersys.repository;

import dev.trajano.mastersys.domain.Regularity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegularityRepository extends JpaRepository<Regularity, Long> {
}