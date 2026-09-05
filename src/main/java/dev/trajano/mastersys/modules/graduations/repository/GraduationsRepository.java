package dev.trajano.mastersys.modules.graduations.repository;

import dev.trajano.mastersys.modules.graduations.model.Graduations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduationsRepository extends JpaRepository<Graduations, Long> {
}
