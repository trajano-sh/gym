package dev.trajano.mastersys.controller;

import dev.trajano.mastersys.dto.StudentsFilterRequestDTO;
import dev.trajano.mastersys.dto.StudentsRequestDTO;
import dev.trajano.mastersys.dto.StudentsResponseDTO;
import dev.trajano.mastersys.service.StudentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentsController {
    private final StudentsService studentsService;

    @PostMapping
    public ResponseEntity<StudentsResponseDTO> register(@RequestBody @Valid StudentsRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentsService.register(requestDTO));
    }

    @GetMapping
    public ResponseEntity<Page<StudentsResponseDTO>> list(StudentsFilterRequestDTO filter, Pageable pageable) {
        return ResponseEntity.ok(studentsService.listStudents(filter,pageable));
    }

    @GetMapping("/{studentsId}")
    public ResponseEntity<StudentsResponseDTO> findStudentsById(@PathVariable Long studentsId) {
        return ResponseEntity.ok(studentsService.findStudentsById(studentsId));
    }

    @PutMapping("/{studentsId}")
    public ResponseEntity<StudentsResponseDTO> update(@PathVariable Long studentsId, @RequestBody @Valid StudentsRequestDTO requestDTO) {
        return ResponseEntity.ok(studentsService.update(studentsId, requestDTO));
    }

    @DeleteMapping("/{studentsId}")
    public ResponseEntity<Void> delete(@PathVariable Long studentsId) {
        studentsService.delete(studentsId);
        return ResponseEntity.noContent().build();
    }
}
