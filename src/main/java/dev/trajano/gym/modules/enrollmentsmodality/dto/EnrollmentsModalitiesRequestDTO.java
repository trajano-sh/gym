package dev.trajano.gym.modules.enrollmentsmodality.dto;

import jakarta.validation.constraints.NotNull;

public record EnrollmentsModalitiesRequestDTO(
        @NotNull Long enrollmentId,
        @NotNull Long modalityId,
        @NotNull Long graduationsId,
        @NotNull Long planId
) {
}
