package dev.trajano.gym.modules.plan.dto;

import java.math.BigDecimal;

public record PlanResponseDTO(
        Long id,
        Long modalityId,
        String name,
        BigDecimal monthlyAmount,
        Boolean active
) {
}
