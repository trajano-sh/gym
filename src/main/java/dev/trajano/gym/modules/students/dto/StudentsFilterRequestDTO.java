package dev.trajano.gym.modules.students.dto;

public record StudentsFilterRequestDTO(
        String name,
        String email,
        String cellPhone,
        String city,
        String state
) {
}
