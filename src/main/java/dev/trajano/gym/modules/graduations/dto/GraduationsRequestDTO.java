package dev.trajano.gym.modules.graduations.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GraduationsRequestDTO(
        @NotNull Long modalityId,
        @NotBlank @Size(max = 100) String name
) {
}
