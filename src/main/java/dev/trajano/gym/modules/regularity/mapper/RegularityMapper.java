package dev.trajano.gym.modules.regularity.mapper;

import dev.trajano.gym.modules.enrollment.domain.Enrollment;
import dev.trajano.gym.modules.regularity.domain.Regularity;
import dev.trajano.gym.modules.regularity.dto.EnrollmentStatisticsResponseDTO;
import dev.trajano.gym.modules.regularity.dto.RegularityResponseDTO;
import dev.trajano.gym.modules.regularity.dto.StatisticsRegularityEnrollmentDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class RegularityMapper {
    public Regularity toEntity(Enrollment enrollment) {
        Regularity regularity = new Regularity();
        regularity.setEnrollmentsId(enrollment);
        return regularity;
    }

    public RegularityResponseDTO fromEntity(Regularity regularity) {
        return new RegularityResponseDTO(regularity.getId(), regularity.getEnrollmentsId().getStudentId().getName(), regularity.getEntryDate());
    }

    public StatisticsRegularityEnrollmentDTO toResponseTop10(List<Regularity> regularities, LocalDate from, LocalDate to) {

        Map<Enrollment, Long> frequencyMap = regularities.stream().collect(Collectors.groupingBy(Regularity::getEnrollmentsId, Collectors.counting()));

        AtomicInteger positionCounter = new AtomicInteger(1);

        List<EnrollmentStatisticsResponseDTO> top10List = frequencyMap.entrySet().stream().sorted(Map.Entry.<Enrollment, Long>comparingByValue().reversed()).limit(10).map(entry -> {
            Enrollment enrollment = entry.getKey();
            Integer totalVisits = entry.getValue().intValue();

            return new EnrollmentStatisticsResponseDTO(positionCounter.getAndIncrement(), enrollment.getId(), enrollment.getStudentId().getName(), enrollment.getStudentId().getCreatedAt(), totalVisits);
        }).toList();

        return new StatisticsRegularityEnrollmentDTO(top10List, from, to);
    }
}
