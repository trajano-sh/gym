package dev.trajano.gym.modules.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ResetPasswordRequestDTO(
        @NotBlank
        @Size(min = 8, max = 150, message = "")
        String passwordActual,

        @NotBlank
        @Size(min = 8,max = 150, message = "")
        String newPassword,

        @NotBlank
        @Size(min = 8, max = 150, message = "")
        String confirmNewPassword) {
}
