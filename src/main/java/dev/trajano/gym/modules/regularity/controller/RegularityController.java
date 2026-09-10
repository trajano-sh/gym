package dev.trajano.gym.modules.regularity.controller;

import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.regularity.dto.RegularityResponseDTO;
import dev.trajano.gym.modules.regularity.dto.StatisticsRegularityEnrollmentDTO;
import dev.trajano.gym.modules.regularity.service.RegularityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/regularity")
public class RegularityController {
    private final RegularityService regularityService;

    @PostMapping("/user/{enrollmentId}")
    public ResponseEntity<Void> register(@PathVariable Long enrollmentId) {
        regularityService.createRegularity(enrollmentId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PageResponse<RegularityResponseDTO>> list(Pageable pageable) {
        PageResponse<RegularityResponseDTO> response = regularityService.listRegularity(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{enrollmentId}")
    public ResponseEntity<PageResponse<RegularityResponseDTO>> findRegularityById(@PathVariable Long enrollmentId, Pageable pageable) {
        PageResponse<RegularityResponseDTO> response = regularityService.listRegularityByEnrollment(enrollmentId,pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/top")
    public ResponseEntity<StatisticsRegularityEnrollmentDTO> listTop20(
            @RequestParam(name = "from")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,
            @RequestParam(name = "to")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to
    ) {
        return ResponseEntity.ok(regularityService.listTop10(from, to));
    }

    @DeleteMapping("/{regularityId}")
    public ResponseEntity<Void> delete(@PathVariable Long regularityId) {
        regularityService.deleteRegularityById(regularityId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
