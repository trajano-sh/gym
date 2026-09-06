package dev.trajano.gym.modules.enrollments.service;

import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.enrollments.dto.EnrollmentsRequestDTO;
import dev.trajano.gym.modules.enrollments.dto.EnrollmentsResponseDTO;
import dev.trajano.gym.modules.enrollments.mapper.EnrollmentsMapper;
import dev.trajano.gym.modules.enrollments.domain.Enrollments;
import dev.trajano.gym.modules.enrollments.repository.EnrollmentsRepository;
import dev.trajano.gym.modules.students.domain.Students;
import dev.trajano.gym.modules.students.service.StudentsService;
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
    private final StudentsService studentsService;

    @Transactional
    public EnrollmentsResponseDTO createEnrollment(EnrollmentsRequestDTO requestDTO) {
        Students students = studentsService.findById(requestDTO.studentId());
        Enrollments enrollments = enrollmentsMapper.toEntity(students, requestDTO);
        enrollmentsRepository.save(enrollments);
        return enrollmentsMapper.fromEntity(enrollments);
    }

    @Transactional(readOnly = true)
    public EnrollmentsResponseDTO searchEnrollmentById(Long enrollmentsId) {
        Enrollments enrollments = findById(enrollmentsId);
        return enrollmentsMapper.fromEntity(enrollments);
    }

    @Transactional(readOnly = true)
    public PageResponse<EnrollmentsResponseDTO> listEnrollments(Pageable pageable){
        Page<Enrollments> enrollments = enrollmentsRepository.findAll(pageable);
        Page<EnrollmentsResponseDTO> enrollmentsResponseDTOS = enrollments.map(enrollmentsMapper::fromEntity);
        return PageResponse.fromPage(enrollmentsResponseDTOS);
    }

    @Transactional
    public void deleteEnrollment(Long enrollmentsId) {
        Enrollments enrollments = findById(enrollmentsId);
        enrollmentsRepository.delete(enrollments);
    }

    private Enrollments findById(Long enrollmentsId) {
        Enrollments enrollments = enrollmentsRepository.findById(enrollmentsId).orElseThrow(() -> new NotFoundException("Enrollments Not Found"));
        return enrollments;
    }
}
