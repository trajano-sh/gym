package dev.trajano.gym.modules.regularity.repository;

import dev.trajano.gym.modules.regularity.domain.Regularity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegularityRepository extends JpaRepository<Regularity, Long> {
    Page<Regularity> findByEnrollmentsId_Id(Long enrollmentId, Pageable pageable);
}