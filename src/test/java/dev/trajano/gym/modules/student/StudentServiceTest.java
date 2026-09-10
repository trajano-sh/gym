package dev.trajano.gym.modules.student;

import dev.trajano.gym.modules.student.domain.SexEnum;
import dev.trajano.gym.modules.student.domain.Student;
import dev.trajano.gym.modules.student.dto.StudentRequestDTO;
import dev.trajano.gym.modules.student.dto.StudentResponseDTO;
import dev.trajano.gym.modules.student.mapper.StudentMapper;
import dev.trajano.gym.modules.student.repository.StudentRepository;
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
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;


    private StudentRequestDTO request;
    private Student student;
    private StudentResponseDTO response;

    @BeforeEach
    void setUp() {
        request = new StudentRequestDTO(
                "Hugo",
                LocalDate.of(2001, 1, 1),
                SexEnum.M,
                "11987654321",
                "11999999999",
                "hugo@gmail.com",
                null,
                "Rua test",
                "100",
                null,
                "Brickell",
                "Miami",
                "Florida",
                "0000000");
        student = new Student();
        response = mock(StudentResponseDTO.class);
    }

    @Test
    void shouldRegisterStudentSuccessfully() {
        when(studentRepository.existsByEmail(request.email())).thenReturn(false);
        when(studentMapper.toEntity(request)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.fromEntity(student)).thenReturn(response);
        StudentResponseDTO result = studentService.register(request);
        assertNotNull(result);
        assertEquals(response, result);

        verify(studentRepository).existsByEmail(request.email());
        verify(studentMapper).toEntity(request);
        verify(studentRepository).save(student);
        verify(studentMapper).fromEntity(student);
    }
}
