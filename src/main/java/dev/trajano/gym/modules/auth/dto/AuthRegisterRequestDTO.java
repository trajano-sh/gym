package dev.trajano.gym.modules.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRegisterRequestDTO(
        @NotBlank(message = "Username cannot be null")
        @Size(max = 150, message = "Max username character 150")
        String username,

        @NotBlank(message = "Password cannot be null")
        @Size(max = 150,message = "Mex password character 150")
        String password
) {
}
