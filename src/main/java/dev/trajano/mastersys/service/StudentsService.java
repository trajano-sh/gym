package dev.trajano.mastersys.service;

import dev.trajano.mastersys.domain.Students;
import dev.trajano.mastersys.dto.StudentsFilterRequestDTO;
import dev.trajano.mastersys.dto.StudentsRequestDTO;
import dev.trajano.mastersys.dto.StudentsResponseDTO;
import dev.trajano.mastersys.exception.AlreadyExistsException;
import dev.trajano.mastersys.exception.NotFoundException;
import dev.trajano.mastersys.repository.StudentsRepository;
import dev.trajano.mastersys.specification.StudentsSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentsService {
    private final StudentsRepository studentsRepository;

    public StudentsResponseDTO register(StudentsRequestDTO requestDTO) {
        if (requestDTO.email() != null && studentsRepository.existsByEmail(requestDTO.email()))
            throw new AlreadyExistsException("E-mail Already Exists");

        Students students = requestDTO.toEntity();
        Students studentsSave = studentsRepository.save(students);
        return StudentsResponseDTO.fromEntity(studentsSave);
    }

    public Page<StudentsResponseDTO> listStudents(StudentsFilterRequestDTO filter, Pageable pageable) {
        return studentsRepository.findAll(StudentsSpecification.filters(filter), pageable).map(StudentsResponseDTO::fromEntity);
    }

    public StudentsResponseDTO findStudentsById(Long idStudents) {
        Students students = findById(idStudents);
        return StudentsResponseDTO.fromEntity(students);
    }

    public StudentsResponseDTO update(Long idStudents, StudentsRequestDTO requestDTO) {
        Students students = findById(idStudents);
        requestDTO.lora(students);
        Students studentsUpdate = studentsRepository.save(students);
        return StudentsResponseDTO.fromEntity(studentsUpdate);
    }

    public void delete(Long id) {
        Students students = findById(id);
        studentsRepository.delete(students);
    }

    private Students findById(Long idStudents) {
        return studentsRepository.findById(idStudents).orElseThrow(() -> new NotFoundException("Students Not Found."));
    }

}
