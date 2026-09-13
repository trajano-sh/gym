package dev.trajano.gym.modules.enrollmentsmodality.service;

import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.enrollment.domain.Enrollment;
import dev.trajano.gym.modules.enrollment.service.EnrollmentsService;
import dev.trajano.gym.modules.enrollmentsmodality.domain.EnrollmentsModalities;
import dev.trajano.gym.modules.enrollmentsmodality.dto.EnrollmentsModalitiesResponseDTO;
import dev.trajano.gym.modules.enrollmentsmodality.dto.EnrollmentModalitiesRequestDTO;
import dev.trajano.gym.modules.enrollmentsmodality.mapper.EnrollmentsModalitiesMapper;
import dev.trajano.gym.modules.enrollmentsmodality.repository.EnrollmentsModalitiesRepository;
import dev.trajano.gym.modules.graduation.domain.Graduations;
import dev.trajano.gym.modules.graduation.service.GraduationsService;
import dev.trajano.gym.modules.modality.domain.Modalities;
import dev.trajano.gym.modules.modality.service.ModalitiesService;
import dev.trajano.gym.modules.plan.domain.Plan;
import dev.trajano.gym.modules.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentModalitiesService {
    private final EnrollmentsModalitiesRepository enrollmentsModalitiesRepository;
    private final EnrollmentsModalitiesMapper enrollmentsModalitiesMapper;
    private final EnrollmentsService enrollmentsService;
    private final ModalitiesService modalitiesService;
    private final GraduationsService graduationsService;
    private final PlanService planService;


    public EnrollmentsModalitiesResponseDTO register(EnrollmentModalitiesRequestDTO request) {
        Enrollment enrollment = enrollmentsService.findById(request.enrollmentId());
        Modalities modalities = modalitiesService.findById(request.modalityId());
        Graduations graduations = graduationsService.findById(request.graduationsId());
        Plan plan = planService.findById(request.planId());

        EnrollmentsModalities enrollmentsModalities = enrollmentsModalitiesMapper.toEntity(enrollment, modalities, graduations, plan);
        enrollmentsModalitiesRepository.save(enrollmentsModalities);
        return enrollmentsModalitiesMapper.fromEntity(enrollmentsModalities);
    }

    @Transactional(readOnly = true)
    public PageResponse<EnrollmentsModalitiesResponseDTO> list(Pageable pageable) {
        Page<EnrollmentsModalities> enrollmentsModalities = enrollmentsModalitiesRepository.findAll(pageable);
        Page<EnrollmentsModalitiesResponseDTO> enrollmentsModalitiesResponseDTOS = enrollmentsModalities.map(enrollmentsModalitiesMapper::fromEntity);
        return PageResponse.fromPage(enrollmentsModalitiesResponseDTOS);
    }

    public EnrollmentsModalitiesResponseDTO findEnrollmentModalitiesById(Long enrollmentModalitiesId){
        EnrollmentsModalities enrollmentsModalities = findById(enrollmentModalitiesId);
        return enrollmentsModalitiesMapper.fromEntity(enrollmentsModalities);
    }

    public EnrollmentsModalitiesResponseDTO update(Long enrollmentModalitiesId, EnrollmentModalitiesRequestDTO request){
        EnrollmentsModalities enrollmentsModalities = findById(enrollmentModalitiesId);
        Enrollment enrollmentUpdate = enrollmentsService.findById(request.enrollmentId());
        Modalities modalitiesUpdate = modalitiesService.findById(request.modalityId());
        Graduations graduationsUpdate = graduationsService.findById(request.graduationsId());
        Plan planUpdate = planService.findById(request.planId());
        enrollmentsModalitiesMapper.toUpdate(enrollmentsModalities,enrollmentUpdate,graduationsUpdate,modalitiesUpdate,planUpdate);
        enrollmentsModalitiesRepository.save(enrollmentsModalities);
        return enrollmentsModalitiesMapper.fromEntity(enrollmentsModalities);
    }

    public void delete(Long enrollmentModalitiesId){
        EnrollmentsModalities enrollmentsModalities = findById(enrollmentModalitiesId);
        enrollmentsModalitiesRepository.delete(enrollmentsModalities);
    }

    public EnrollmentsModalities findById(Long enrollmentsModalitiesId) {
        return enrollmentsModalitiesRepository.findById(enrollmentsModalitiesId).orElseThrow(() -> new NotFoundException("EnrollmentsModalities Not Found"));
    }
}
