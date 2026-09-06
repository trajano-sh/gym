package dev.trajano.gym.modules.student.dto;

import dev.trajano.gym.modules.student.domain.SexEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentResponseDTO(Long id,
                                 String name,
                                 LocalDate dateOfBirth,
                                 SexEnum sex,
                                 String cellPhone,
                                 String email,
                                 String city,
                                 String estate,
                                 LocalDateTime createdAt
) {
}
