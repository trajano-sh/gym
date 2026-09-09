package dev.trajano.gym.modules.regularity.dto;

import java.time.LocalDate;
import java.util.List;

public record StatisticsRegularityEnrollmentDTO(
        List<EnrollmentResponseDTO> enrollments,
        LocalDate from,
        LocalDate to
) {
}
