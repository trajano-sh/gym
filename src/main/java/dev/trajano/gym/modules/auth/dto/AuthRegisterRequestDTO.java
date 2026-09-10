package dev.trajano.gym.modules.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRegisterRequestDTO(
        @NotBlank(message = "Username cannot be null")
        @Size(min = 3, max = 150, message = "The username must be between 3 and 150 characters long.")
        String username,

        @NotBlank(message = "Password cannot be null")
        @Size(min = 8, max = 150, message = "The username must be between 8 and 150 characters long.")
        String password) {
}
