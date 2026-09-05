package dev.trajano.gym.modules.plans.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record PlansRequestDTO(@NotNull
                              Long modalityId,

                              @NotBlank(message = "Name is required")
                              @Size(max = 100)
                              String name,

                              @Positive(message = "The value must be positive")
                              BigDecimal monthlyAmount
) {
}
