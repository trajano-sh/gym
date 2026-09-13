package dev.trajano.gym.modules.enrollmentsmodality.controller;

import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.enrollment.dto.EnrollmentRequestDTO;
import dev.trajano.gym.modules.enrollment.service.EnrollmentsService;
import dev.trajano.gym.modules.enrollmentsmodality.domain.EnrollmentsModalities;
import dev.trajano.gym.modules.enrollmentsmodality.dto.EnrollmentModalitiesRequestDTO;
import dev.trajano.gym.modules.enrollmentsmodality.dto.EnrollmentsModalitiesResponseDTO;
import dev.trajano.gym.modules.enrollmentsmodality.service.EnrollmentModalitiesService;
import dev.trajano.gym.modules.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollment-modalities")
public class EnrollmentModalitiesController {
    private final EnrollmentModalitiesService enrollmentModalitiesService;

    @PostMapping
    public ResponseEntity<EnrollmentsModalitiesResponseDTO> create(@RequestBody EnrollmentModalitiesRequestDTO request) {
        EnrollmentsModalitiesResponseDTO response = enrollmentModalitiesService.register(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageResponse<EnrollmentsModalitiesResponseDTO>> list(Pageable pageable){
        PageResponse<EnrollmentsModalitiesResponseDTO> response = enrollmentModalitiesService.list(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{enrollmentModalitiesId}")
    public ResponseEntity<EnrollmentsModalitiesResponseDTO> update(@PathVariable Long enrollmentModalitiesId,@RequestBody EnrollmentModalitiesRequestDTO request){
        EnrollmentsModalitiesResponseDTO response = enrollmentModalitiesService.update(enrollmentModalitiesId,request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{enrollmentModalitiesId}")
    public ResponseEntity<EnrollmentsModalitiesResponseDTO> findEnrollmentModalitiesById(@PathVariable Long enrollmentModalitiesId){
        EnrollmentsModalitiesResponseDTO response = enrollmentModalitiesService.findEnrollmentModalitiesById(enrollmentModalitiesId);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{enrollmentModalitiesId}")
    public ResponseEntity<Void> delete(@PathVariable Long enrollmentModalitiesId){
        enrollmentModalitiesService.delete(enrollmentModalitiesId);
        return ResponseEntity.noContent().build();
    }

}
