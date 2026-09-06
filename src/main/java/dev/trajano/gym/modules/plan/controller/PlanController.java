package dev.trajano.gym.modules.plan.controller;

import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.plan.dto.PlanRequestDTO;
import dev.trajano.gym.modules.plan.dto.PlanResponseDTO;
import dev.trajano.gym.modules.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plans")
public class PlanController {
    private final PlanService planService;

    @GetMapping
    public ResponseEntity<PageResponse<PlanResponseDTO>> listPlans(Pageable pageable) {
        return ResponseEntity.ok(planService.listPlans(pageable));
    }

    @PostMapping
    public ResponseEntity<PlanResponseDTO> createPlan(@RequestBody PlanRequestDTO requestDTO) {
        return ResponseEntity.ok(planService.createPlan(requestDTO));
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanResponseDTO> searchPlanById(@PathVariable Long planId) {
        return ResponseEntity.ok(planService.searchPlanById(planId));
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long planId) {
        planService.deletePlan(planId);
        return ResponseEntity.noContent().build();
    }
}
