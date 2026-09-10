package dev.trajano.gym.modules.enrollment.controller;

import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.enrollment.dto.EnrollmentRequestDTO;
import dev.trajano.gym.modules.enrollment.dto.EnrollmentResponseDTO;
import dev.trajano.gym.modules.enrollment.service.EnrollmentsService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollments")
@RateLimiter(name = "enrollment")
public class EnrollmentController {
    private final EnrollmentsService enrollmentsService;

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> register(@RequestBody @Valid EnrollmentRequestDTO request) {
        EnrollmentResponseDTO response = enrollmentsService.createEnrollment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{enrollmentsId}")
    public ResponseEntity<EnrollmentResponseDTO> findEnrollmentById(@PathVariable Long enrollmentsId) {
        EnrollmentResponseDTO response = enrollmentsService.searchEnrollmentById(enrollmentsId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageResponse<EnrollmentResponseDTO>> list(Pageable pageable) {
        PageResponse<EnrollmentResponseDTO> response = enrollmentsService.listEnrollments(pageable);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{enrollmentsId}")
    public ResponseEntity<Void> delete(@PathVariable Long enrollmentsId) {
        enrollmentsService.deleteEnrollment(enrollmentsId);
        return ResponseEntity.noContent().build();
    }
}
