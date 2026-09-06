package dev.trajano.gym.modules.graduation.repository;

import dev.trajano.gym.modules.graduation.domain.Graduations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduationsRepository extends JpaRepository<Graduations, Long> {
}
