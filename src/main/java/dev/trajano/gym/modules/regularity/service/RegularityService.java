package dev.trajano.gym.modules.regularity.service;

import dev.trajano.gym.core.exception.BusinessException;
import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.enrollment.domain.Enrollment;
import dev.trajano.gym.modules.enrollment.service.EnrollmentsService;
import dev.trajano.gym.modules.regularity.domain.Regularity;
import dev.trajano.gym.modules.regularity.dto.RegularityResponseDTO;
import dev.trajano.gym.modules.regularity.dto.StatisticsRegularityEnrollmentDTO;
import dev.trajano.gym.modules.regularity.mapper.RegularityMapper;
import dev.trajano.gym.modules.regularity.repository.RegularityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegularityService {
    private final RegularityMapper regularityMapper;
    private final RegularityRepository regularityRepository;
    private final EnrollmentsService enrollmentsService;

    @Transactional
    public void createRegularity(Long enrollmentId) {
        var enrollment = enrollmentsService.findById(enrollmentId);
        Regularity regularity = regularityMapper.toEntity(enrollment);
        regularityRepository.save(regularity);
    }

    @Transactional(readOnly = true)
    public PageResponse<RegularityResponseDTO> listRegularity(Pageable pageable) {
        Page<Regularity> regularities = regularityRepository.findAll(pageable);
        Page<RegularityResponseDTO> regularityResponseDTOS = regularities.map(regularityMapper::fromEntity);
        return PageResponse.fromPage(regularityResponseDTOS);
    }

    @Transactional(readOnly = true)
    public PageResponse<RegularityResponseDTO> listRegularityByEnrollment(Long enrollmentId, Pageable pageable) {
        Page<Regularity> regularitiesEnrollment = regularityRepository.findByEnrollmentsId_Id(enrollmentId, pageable);
        Page<RegularityResponseDTO> regularityResponseDTOS = regularitiesEnrollment.map(regularityMapper::fromEntity);
        return PageResponse.fromPage(regularityResponseDTOS);
    }

    @Transactional(readOnly = true)
    public StatisticsRegularityEnrollmentDTO listTop10(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            throw new BusinessException("The start and end dates are mandatory.");
        }

        LocalDate today = LocalDate.now();

        if (from.isAfter(today) || to.isAfter(today)) {
            throw new BusinessException("The dates provided cannot be in the future.");
        }

        if (from.isBefore(to)) {
            throw new BusinessException("The end date cannot be earlier than the start date.");
        }
        List<Regularity> regularities = regularityRepository.findAll();

        return regularityMapper.toResponseTop10(regularities, from, to);
    }

    @Transactional
    public void deleteRegularityById(Long regularityId) {
        Regularity regularity = findByRegularity(regularityId);
        regularityRepository.delete(regularity);
    }

    private Regularity findByRegularity(Long regularityId) {
        Regularity regularity = regularityRepository.findById(regularityId)
                .orElseThrow(() -> new NotFoundException("Regularity Not Found."));
        return regularity;
    }
}
