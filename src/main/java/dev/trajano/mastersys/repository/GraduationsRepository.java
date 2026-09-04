package dev.trajano.mastersys.repository;

import dev.trajano.mastersys.domain.Graduations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduationsRepository extends JpaRepository<Graduations, Long> {
}
