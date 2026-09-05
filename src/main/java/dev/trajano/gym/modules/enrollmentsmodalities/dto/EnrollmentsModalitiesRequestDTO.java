package dev.trajano.gym.modules.enrollmentsmodalities.dto;

import jakarta.validation.constraints.NotNull;

public record EnrollmentsModalitiesRequestDTO(
        @NotNull Long enrollmentId,
        @NotNull Long modalityId,
        @NotNull Long graduationsId,
        @NotNull Long planId
) {
}
