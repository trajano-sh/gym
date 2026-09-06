package dev.trajano.gym.modules.graduations.repository;

import dev.trajano.gym.modules.graduations.domain.Graduations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduationsRepository extends JpaRepository<Graduations, Long> {
}
