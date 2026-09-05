package dev.trajano.gym.modules.students.service;

import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.students.dto.StudentsFilterRequestDTO;
import dev.trajano.gym.modules.students.dto.StudentsRequestDTO;
import dev.trajano.gym.modules.students.dto.StudentsResponseDTO;
import dev.trajano.gym.core.exception.AlreadyExistsException;
import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.modules.students.mapper.StudentsMapper;
import dev.trajano.gym.modules.students.specification.StudentsSpecification;
import dev.trajano.gym.modules.students.model.Students;
import dev.trajano.gym.modules.students.repository.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentsService {
    private final StudentsRepository studentsRepository;
    private final StudentsMapper studentsMapper;

    @Transactional
    public StudentsResponseDTO register(StudentsRequestDTO requestDTO) {
        if (requestDTO.email() != null && studentsRepository.existsByEmail(requestDTO.email()))
            throw new AlreadyExistsException("E-mail Already Exists");

        Students students = studentsMapper.toEntity(requestDTO);
        Students studentsSave = studentsRepository.save(students);
        return studentsMapper.fromEntity(studentsSave);
    }

    @Transactional(readOnly = true)
    public PageResponse<StudentsResponseDTO> listStudents(StudentsFilterRequestDTO filter, Pageable pageable) {
        Page<Students> students = studentsRepository.findAll(StudentsSpecification.filters(filter), pageable);
        Page<StudentsResponseDTO> studentsResponseDTOS = students.map(studentsMapper::fromEntity);
        return PageResponse.fromPage(studentsResponseDTOS);
    }


    @Transactional(readOnly = true)
    public StudentsResponseDTO findStudentsById(Long idStudents) {
        Students students = findById(idStudents);
        return studentsMapper.fromEntity(students);
    }

    @Transactional
    public StudentsResponseDTO update(Long idStudents, StudentsRequestDTO requestDTO) {
        Students students = findById(idStudents);
        studentsMapper.toUpdate(students, requestDTO);
        Students studentsUpdate = studentsRepository.save(students);
        return studentsMapper.fromEntity(studentsUpdate);
    }

    @Transactional
    public void delete(Long id) {
        Students students = findById(id);
        studentsRepository.delete(students);
    }

    public Students findById(Long idStudents) {
        return studentsRepository.findById(idStudents).orElseThrow(() -> new NotFoundException("Students Not Found."));
    }

}
