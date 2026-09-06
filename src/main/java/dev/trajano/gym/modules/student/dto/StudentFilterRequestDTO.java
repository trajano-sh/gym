package dev.trajano.gym.modules.student.dto;

public record StudentFilterRequestDTO(
        String name,
        String email,
        String cellPhone,
        String city,
        String state
) {
}
