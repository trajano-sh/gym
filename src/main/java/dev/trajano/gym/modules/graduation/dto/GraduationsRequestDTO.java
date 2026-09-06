package dev.trajano.gym.modules.graduation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GraduationsRequestDTO(
        @NotNull Long modalityId,
        @NotBlank @Size(max = 100) String name
) {
}
