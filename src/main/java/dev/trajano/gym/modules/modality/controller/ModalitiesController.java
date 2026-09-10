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
    public ResponseEntity<ModalitiesResponseDTO> register(@RequestBody ModalitiesRequestDTO request) {
        ModalitiesResponseDTO response = modalitiesService.createModality(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{modalityId}")
    public ResponseEntity<ModalitiesResponseDTO> findModalitiesById(@PathVariable Long modalityId) {
        ModalitiesResponseDTO response = modalitiesService.searchModalityById(modalityId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageResponse<ModalitiesResponseDTO>> list(Pageable pageable) {
        PageResponse<ModalitiesResponseDTO> response = modalitiesService.listModalities(pageable);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{modalityId}")
    public ResponseEntity<Void> delete(@PathVariable Long modalityId) {
        modalitiesService.deleteModalities(modalityId);
        return ResponseEntity.noContent().build();
    }
}
