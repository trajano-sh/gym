package dev.trajano.gym.modules.enrollments.mapper;

import dev.trajano.gym.modules.enrollments.dto.EnrollmentsRequestDTO;
import dev.trajano.gym.modules.enrollments.dto.EnrollmentsResponseDTO;
import dev.trajano.gym.modules.enrollments.model.Enrollments;
import dev.trajano.gym.modules.students.model.Students;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentsMapper {
    public Enrollments toEntity(Students students, EnrollmentsRequestDTO requestDTO){
        Enrollments enrollments = new Enrollments();
        enrollments.setStudentId(students);
        enrollments.setDayMaturity(requestDTO.dayMaturity());
        return enrollments;
    }

    public EnrollmentsResponseDTO fromEntity(Enrollments enrollments){
        return new EnrollmentsResponseDTO(
                enrollments.getId(),
                enrollments.getStudentId().getId(),
                enrollments.getEnrollmentDate(),
                enrollments.getDayMaturity(),
                enrollments.getStatus()
        );
    }
}
