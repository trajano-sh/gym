package dev.trajano.gym.modules.enrollment.service;

import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.enrollment.dto.EnrollmentRequestDTO;
import dev.trajano.gym.modules.enrollment.dto.EnrollmentResponseDTO;
import dev.trajano.gym.modules.enrollment.mapper.EnrollmentsMapper;
import dev.trajano.gym.modules.enrollment.domain.Enrollment;
import dev.trajano.gym.modules.enrollment.repository.EnrollmentsRepository;
import dev.trajano.gym.modules.student.domain.Student;
import dev.trajano.gym.modules.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnrollmentsService {
    private final EnrollmentsRepository enrollmentsRepository;
    private final EnrollmentsMapper enrollmentsMapper;
    private final StudentService studentService;

    @Transactional
    public EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO requestDTO) {
        Student student = studentService.findById(requestDTO.studentId());
        Enrollment enrollments = enrollmentsMapper.toEntity(student, requestDTO);
        enrollmentsRepository.save(enrollments);
        return enrollmentsMapper.fromEntity(enrollments);
    }

    @Transactional(readOnly = true)
    public EnrollmentResponseDTO searchEnrollmentById(Long enrollmentsId) {
        Enrollment enrollments = findById(enrollmentsId);
        return enrollmentsMapper.fromEntity(enrollments);
    }

    @Transactional(readOnly = true)
    public PageResponse<EnrollmentResponseDTO> listEnrollments(Pageable pageable){
        Page<Enrollment> enrollments = enrollmentsRepository.findAll(pageable);
        Page<EnrollmentResponseDTO> enrollmentsResponseDTOS = enrollments.map(enrollmentsMapper::fromEntity);
        return PageResponse.fromPage(enrollmentsResponseDTOS);
    }

    @Transactional
    public void deleteEnrollment(Long enrollmentsId) {
        Enrollment enrollments = findById(enrollmentsId);
        enrollmentsRepository.delete(enrollments);
    }

    private Enrollment findById(Long enrollmentsId) {
        Enrollment enrollments = enrollmentsRepository.findById(enrollmentsId).orElseThrow(() -> new NotFoundException("Enrollments Not Found"));
        return enrollments;
    }
}
