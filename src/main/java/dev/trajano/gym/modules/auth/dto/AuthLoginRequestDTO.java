package dev.trajano.gym.modules.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthLoginRequestDTO(
        @NotBlank(message = "Username Is Required")
        @Size(max = 150)
        String username,
        
        @NotBlank(message = "Password Is Required")
        @Size(max = 150)
        String password
) {
}
