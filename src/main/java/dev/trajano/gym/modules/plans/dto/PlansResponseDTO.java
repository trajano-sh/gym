package dev.trajano.gym.modules.plans.dto;

import java.math.BigDecimal;

public record PlansResponseDTO(
        Long id,
        Long modalityId,
        String name,
        BigDecimal monthlyAmount,
        Boolean active
) {
}
