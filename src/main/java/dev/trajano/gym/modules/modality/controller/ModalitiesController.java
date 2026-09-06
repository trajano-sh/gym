package dev.trajano.gym.modules.modality.controller;

import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.modality.dto.ModalitiesRequestDTO;
import dev.trajano.gym.modules.modality.dto.ModalitiesResponseDTO;
import dev.trajano.gym.modules.modality.service.ModalitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/modalities")
public class ModalitiesController {
    private final ModalitiesService modalitiesService;

    @PostMapping
    public ResponseEntity<ModalitiesResponseDTO> createModality(@RequestBody ModalitiesRequestDTO requestDTO) {
        return ResponseEntity.ok(modalitiesService.createModality(requestDTO));
    }

    @GetMapping("/{modalityId}")
    public ResponseEntity<ModalitiesResponseDTO> searchModalityById(@PathVariable Long modalityId) {
        return ResponseEntity.ok(modalitiesService.searchModalityById(modalityId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ModalitiesResponseDTO>> listModalities(Pageable pageable) {
        return ResponseEntity.ok(modalitiesService.listModalities(pageable));
    }

    @DeleteMapping("/{modalityId}")
    public ResponseEntity<Void> deleteModality(@PathVariable Long modalityId) {
        modalitiesService.deleteModalities(modalityId);
        return ResponseEntity.noContent().build();
    }
}
