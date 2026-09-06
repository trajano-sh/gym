package dev.trajano.gym.modules.student.service;

import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.student.domain.Student;
import dev.trajano.gym.modules.student.dto.StudentFilterRequestDTO;
import dev.trajano.gym.modules.student.dto.StudentRequestDTO;
import dev.trajano.gym.modules.student.dto.StudentResponseDTO;
import dev.trajano.gym.core.exception.AlreadyExistsException;
import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.modules.student.mapper.StudentMapper;
import dev.trajano.gym.modules.student.specification.StudentSpecification;
import dev.trajano.gym.modules.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Transactional
    public StudentResponseDTO register(StudentRequestDTO requestDTO) {
        if (requestDTO.email() != null && studentRepository.existsByEmail(requestDTO.email()))
            throw new AlreadyExistsException("E-mail Already Exists");

        Student student = studentMapper.toEntity(requestDTO);
        Student studentSave = studentRepository.save(student);
        return studentMapper.fromEntity(studentSave);
    }

    @Transactional(readOnly = true)
    public PageResponse<StudentResponseDTO> listStudents(StudentFilterRequestDTO filter, Pageable pageable) {
        Page<Student> students = studentRepository.findAll(StudentSpecification.filters(filter), pageable);
        Page<StudentResponseDTO> studentsResponseDTOS = students.map(studentMapper::fromEntity);
        return PageResponse.fromPage(studentsResponseDTOS);
    }


    @Transactional(readOnly = true)
    public StudentResponseDTO findStudentsById(Long idStudents) {
        Student student = findById(idStudents);
        return studentMapper.fromEntity(student);
    }

    @Transactional
    public StudentResponseDTO update(Long idStudents, StudentRequestDTO requestDTO) {
        Student student = findById(idStudents);
        studentMapper.toUpdate(student, requestDTO);
        Student studentUpdate = studentRepository.save(student);
        return studentMapper.fromEntity(studentUpdate);
    }

    @Transactional
    public void delete(Long id) {
        Student student = findById(id);
        studentRepository.delete(student);
    }

    public Student findById(Long idStudents) {
        return studentRepository.findById(idStudents).orElseThrow(() -> new NotFoundException("Students Not Found."));
    }

}
