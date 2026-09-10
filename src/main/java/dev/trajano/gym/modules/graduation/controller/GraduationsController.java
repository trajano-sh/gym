package dev.trajano.gym.modules.graduation.controller;

import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.graduation.dto.GraduationsRequestDTO;
import dev.trajano.gym.modules.graduation.dto.GraduationsResponseDTO;
import dev.trajano.gym.modules.graduation.service.GraduationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/graduations")
public class GraduationsController {
    private final GraduationsService graduationsService;

    @PostMapping
    public ResponseEntity<GraduationsResponseDTO> register(@RequestBody GraduationsRequestDTO request) {
        GraduationsResponseDTO response = graduationsService.createGraduation(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{graduationId}")
    public ResponseEntity<GraduationsResponseDTO> findGraduationById(@PathVariable Long graduationId) {
        GraduationsResponseDTO response = graduationsService.findGraduationResponse(graduationId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageResponse<GraduationsResponseDTO>> list(Pageable pageable) {
        PageResponse<GraduationsResponseDTO> response = graduationsService.listGraduations(pageable);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{graduationId}")
    public ResponseEntity<Void> delete(@PathVariable Long graduationId) {
        graduationsService.deleteGraduation(graduationId);
        return ResponseEntity.noContent().build();
    }
}
