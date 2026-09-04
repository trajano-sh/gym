package dev.trajano.mastersys.repository;

import dev.trajano.mastersys.domain.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Students, Long> {
}
