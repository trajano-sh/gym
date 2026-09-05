package dev.trajano.mastersys.dto;

public record StudentsFilterRequestDTO(
        String name,
        String email,
        String cellPhone,
        String city,
        String state
) {
}
