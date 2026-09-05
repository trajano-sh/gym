package dev.trajano.gym.modules.modalities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModalitiesRequestDTO(
        @NotBlank(message = "Name is required")
        @Size(max = 100,message = "Max name 100 character")
        String name
) {
}
