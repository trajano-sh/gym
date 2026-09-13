package dev.trajano.gym.modules.student.controller;

import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.student.service.StudentService;
import dev.trajano.gym.modules.student.dto.StudentFilterRequestDTO;
import dev.trajano.gym.modules.student.dto.StudentRequestDTO;
import dev.trajano.gym.modules.student.dto.StudentResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> register(@RequestBody @Valid StudentRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.register(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<StudentResponseDTO>> list(StudentFilterRequestDTO filter, Pageable pageable) {
        return ResponseEntity.ok(studentService.listStudents(filter,pageable));
    }

    @GetMapping("/{studentsId}")
    public ResponseEntity<StudentResponseDTO> findStudentsById(@PathVariable Long studentsId) {
        return ResponseEntity.ok(studentService.findStudentById(studentsId));
    }

    @PutMapping("/{studentsId}")
    public ResponseEntity<StudentResponseDTO> update(@PathVariable Long studentsId, @RequestBody @Valid StudentRequestDTO request) {
        return ResponseEntity.ok(studentService.update(studentsId, request));
    }

    @DeleteMapping("/{studentsId}")
    public ResponseEntity<Void> delete(@PathVariable Long studentsId) {
        studentService.delete(studentsId);
        return ResponseEntity.noContent().build();
    }
}
