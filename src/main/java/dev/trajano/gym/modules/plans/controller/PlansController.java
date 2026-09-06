package dev.trajano.gym.modules.plans.controller;

import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.plans.dto.PlansRequestDTO;
import dev.trajano.gym.modules.plans.dto.PlansResponseDTO;
import dev.trajano.gym.modules.plans.service.PlansService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plans")
public class PlansController {
    private final PlansService plansService;

    @GetMapping
    public ResponseEntity<PageResponse<PlansResponseDTO>> listPlans(Pageable pageable) {
        return ResponseEntity.ok(plansService.listPlans(pageable));
    }

    @PostMapping
    public ResponseEntity<PlansResponseDTO> createPlan(@RequestBody PlansRequestDTO requestDTO) {
        return ResponseEntity.ok(plansService.createPlan(requestDTO));
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlansResponseDTO> searchPlanById(@PathVariable Long planId) {
        return ResponseEntity.ok(plansService.searchPlanById(planId));
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long planId) {
        plansService.deletePlan(planId);
        return ResponseEntity.noContent().build();
    }
}
