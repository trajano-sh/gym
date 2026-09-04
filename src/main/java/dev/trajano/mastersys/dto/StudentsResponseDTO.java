package dev.trajano.mastersys.dto;

import dev.trajano.mastersys.domain.Students;
import dev.trajano.mastersys.enums.SexEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentsResponseDTO(Long id, String name, LocalDate dateOfBirth, SexEnum sex, String cellPhone,
                                  String email, String city, String estate, LocalDateTime createdAt) {
    public static StudentsResponseDTO fromEntity(Students students) {
        return new StudentsResponseDTO(students.getId(), students.getName(), students.getDateOfBirth(), students.getSex(), students.getCellPhone(), students.getEmail(), students.getCity(), students.getState(), students.getCreatedAt());
    }
}
