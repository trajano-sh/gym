package dev.trajano.gym.modules.enrollment.controller;

import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.enrollment.dto.EnrollmentRequestDTO;
import dev.trajano.gym.modules.enrollment.dto.EnrollmentResponseDTO;
import dev.trajano.gym.modules.enrollment.service.EnrollmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {
    private final EnrollmentsService enrollmentsService;

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> createEnrollment(@RequestBody EnrollmentRequestDTO requestDTO) {
        return ResponseEntity.ok(enrollmentsService.createEnrollment(requestDTO));
    }

    @GetMapping("/{enrollmentsId}")
    public ResponseEntity<EnrollmentResponseDTO> searchEnrollmentById(@PathVariable Long enrollmentsId) {
        return ResponseEntity.ok(enrollmentsService.searchEnrollmentById(enrollmentsId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<EnrollmentResponseDTO>> listEnrollments(Pageable pageable) {
        return ResponseEntity.ok(enrollmentsService.listEnrollments(pageable));
    }

    @DeleteMapping("/{enrollmentsId}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long enrollmentsId) {
        enrollmentsService.deleteEnrollment(enrollmentsId);
        return ResponseEntity.noContent().build();
    }
}
