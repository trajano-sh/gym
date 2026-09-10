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
    public ResponseEntity<PageResponse<PlanResponseDTO>> list(Pageable pageable) {
        PageResponse<PlanResponseDTO> response = planService.listPlans(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PlanResponseDTO> register(@RequestBody PlanRequestDTO request) {
        PlanResponseDTO response = planService.createPlan(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanResponseDTO> findPlanById(@PathVariable Long planId) {
        PlanResponseDTO response = planService.searchPlanById(planId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> delete(@PathVariable Long planId) {
        planService.deletePlan(planId);
        return ResponseEntity.noContent().build();
    }
}
