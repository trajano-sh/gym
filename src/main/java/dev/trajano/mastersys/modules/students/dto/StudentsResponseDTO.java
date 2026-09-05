package dev.trajano.mastersys.modules.students.dto;

import dev.trajano.mastersys.modules.students.model.Students;
import dev.trajano.mastersys.modules.students.model.SexEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentsResponseDTO(Long id, String name, LocalDate dateOfBirth, SexEnum sex, String cellPhone,
                                  String email, String city, String estate, LocalDateTime createdAt) {
    public static StudentsResponseDTO fromEntity(Students students) {
        return new StudentsResponseDTO(students.getId(), students.getName(), students.getDateOfBirth(), students.getSex(), students.getCellPhone(), students.getEmail(), students.getCity(), students.getState(), students.getCreatedAt());
    }
}
