package dev.trajano.gym.modules.plan.service;

import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.modality.domain.Modalities;
import dev.trajano.gym.modules.modality.repository.ModalitiesRepository;
import dev.trajano.gym.modules.plan.domain.Plan;
import dev.trajano.gym.modules.plan.dto.PlanRequestDTO;
import dev.trajano.gym.modules.plan.dto.PlanResponseDTO;
import dev.trajano.gym.modules.plan.mapper.PlanMapper;
import dev.trajano.gym.modules.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;
    private final PlanMapper planMapper;
    private final ModalitiesRepository modalitiesRepository;

    public PlanResponseDTO createPlan(PlanRequestDTO dto){
        Modalities modality = modalitiesRepository.findById(dto.modalityId())
                .orElseThrow(()->new NotFoundException("Modality Not Found"));
        Plan plan = planMapper.toEntity(modality,dto);
        planMapper.fromEntity(plan);
        planRepository.save(plan);
        return planMapper.fromEntity(plan);
    }

    public PlanResponseDTO searchPlanById(Long planId){
        Plan plan = findById(planId);
        return planMapper.fromEntity(plan);
    }
    public PageResponse<PlanResponseDTO> listPlans(Pageable pageable){
        Page<Plan> plans = planRepository.findAll(pageable);
        Page<PlanResponseDTO> plansResponseDTOPageResponse = plans.map(planMapper::fromEntity);
        return PageResponse.fromPage(plansResponseDTOPageResponse);
    }

    public void deletePlan(Long planId){
        Plan plan = findById(planId);
        planRepository.delete(plan);
    }

    private Plan findById(Long planId){
        Plan plan = planRepository.findById(planId)
                .orElseThrow(()->new NotFoundException("Plan Not Found"));
        return plan;
    }
}
