package dev.trajano.gym.modules.enrollments.controller;

import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.enrollments.dto.EnrollmentsRequestDTO;
import dev.trajano.gym.modules.enrollments.dto.EnrollmentsResponseDTO;
import dev.trajano.gym.modules.enrollments.service.EnrollmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollments")
public class EnrollmentsController {
    private final EnrollmentsService enrollmentsService;

    @PostMapping
    public ResponseEntity<EnrollmentsResponseDTO> createEnrollment(@RequestBody EnrollmentsRequestDTO requestDTO) {
        return ResponseEntity.ok(enrollmentsService.createEnrollment(requestDTO));
    }

    @GetMapping("/{enrollmentsId}")
    public ResponseEntity<EnrollmentsResponseDTO> searchEnrollmentById(@PathVariable Long enrollmentsId) {
        return ResponseEntity.ok(enrollmentsService.searchEnrollmentById(enrollmentsId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<EnrollmentsResponseDTO>> listEnrollments(Pageable pageable) {
        return ResponseEntity.ok(enrollmentsService.listEnrollments(pageable));
    }

    @DeleteMapping("/{enrollmentsId}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long enrollmentsId) {
        enrollmentsService.deleteEnrollment(enrollmentsId);
        return ResponseEntity.noContent().build();
    }
}
