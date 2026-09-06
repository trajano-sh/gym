package dev.trajano.gym.modules.enrollment.mapper;

import dev.trajano.gym.modules.enrollment.dto.EnrollmentRequestDTO;
import dev.trajano.gym.modules.enrollment.dto.EnrollmentResponseDTO;
import dev.trajano.gym.modules.enrollment.domain.Enrollment;
import dev.trajano.gym.modules.student.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentsMapper {
    public Enrollment toEntity(Student student, EnrollmentRequestDTO requestDTO){
        Enrollment enrollments = new Enrollment();
        enrollments.setStudentId(student);
        enrollments.setDayMaturity(requestDTO.dayMaturity());
        return enrollments;
    }

    public EnrollmentResponseDTO fromEntity(Enrollment enrollments){
        return new EnrollmentResponseDTO(
                enrollments.getId(),
                enrollments.getStudentId().getId(),
                enrollments.getEnrollmentDate(),
                enrollments.getDayMaturity(),
                enrollments.getStatus()
        );
    }
}
