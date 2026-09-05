package dev.trajano.mastersys.modules.students.repository;

import dev.trajano.mastersys.modules.students.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentsRepository extends JpaRepository<Students, Long>, JpaSpecificationExecutor<Students> {
    boolean existsByEmail(String email);
}
