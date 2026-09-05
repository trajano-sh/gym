package dev.trajano.mastersys.repository;

import dev.trajano.mastersys.domain.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentsRepository extends JpaRepository<Students, Long>, JpaSpecificationExecutor<Students> {
    boolean existsByEmail(String email);
}
