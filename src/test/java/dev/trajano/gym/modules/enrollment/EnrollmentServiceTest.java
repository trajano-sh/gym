package dev.trajano.gym.modules.enrollment;

import dev.trajano.gym.fixtures.StudentFixture;
import dev.trajano.gym.modules.enrollment.domain.Enrollment;
import dev.trajano.gym.modules.enrollment.dto.EnrollmentRequestDTO;
import dev.trajano.gym.modules.enrollment.dto.EnrollmentResponseDTO;
import dev.trajano.gym.modules.enrollment.mapper.EnrollmentsMapper;
import dev.trajano.gym.modules.enrollment.repository.EnrollmentsRepository;
import dev.trajano.gym.modules.enrollment.service.EnrollmentsService;
import dev.trajano.gym.modules.student.domain.Student;
import dev.trajano.gym.modules.student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {

    @Mock
    private EnrollmentsRepository enrollmentRepository;

    @Mock
    private EnrollmentsMapper enrollmentsMapper;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private EnrollmentsService enrollmentsService;

    private EnrollmentResponseDTO response;
    private Enrollment enrollment;
    private Student student;
    private EnrollmentRequestDTO request;

    @BeforeEach
    void setUp() {
        student = StudentFixture.studentWithId(1L);

        request = new EnrollmentRequestDTO(1L, LocalDate.of(2006, 1, 1));

        enrollment = new Enrollment();
        response = mock(EnrollmentResponseDTO.class);
    }

    @Test
    void shouldRegisterEnrollmentSuccessfully() {

        when(studentService.findById(request.studentId())).thenReturn(student);

        when(enrollmentsMapper.toEntity(student, request)).thenReturn(enrollment);

        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);

        when(enrollmentsMapper.fromEntity(enrollment)).thenReturn(response);

        EnrollmentResponseDTO result = enrollmentsService.createEnrollment(request);

        assertNotNull(result);
        assertEquals(response, result);

        verify(studentService).findById(request.studentId());
        verify(enrollmentsMapper).toEntity(student, request);
        verify(enrollmentRepository).save(enrollment);
        verify(enrollmentsMapper).fromEntity(enrollment);
    }
}