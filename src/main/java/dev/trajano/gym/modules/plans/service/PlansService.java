package dev.trajano.gym.modules.plans.service;

import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.modalities.domain.Modalities;
import dev.trajano.gym.modules.modalities.repository.ModalitiesRepository;
import dev.trajano.gym.modules.plans.dto.PlansRequestDTO;
import dev.trajano.gym.modules.plans.dto.PlansResponseDTO;
import dev.trajano.gym.modules.plans.mapper.PlansMapper;
import dev.trajano.gym.modules.plans.domain.Plans;
import dev.trajano.gym.modules.plans.repository.PlansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlansService {
    private final PlansRepository plansRepository;
    private final PlansMapper plansMapper;
    private final ModalitiesRepository modalitiesRepository;

    public PlansResponseDTO createPlan(PlansRequestDTO dto){
        Modalities modality = modalitiesRepository.findById(dto.modalityId())
                .orElseThrow(()->new NotFoundException("Modality Not Found"));
        Plans plans = plansMapper.toEntity(modality,dto);
        plansMapper.fromEntity(plans);
        plansRepository.save(plans);
        return plansMapper.fromEntity(plans);
    }

    public PlansResponseDTO searchPlanById(Long planId){
        Plans plan = findById(planId);
        return plansMapper.fromEntity(plan);
    }
    public PageResponse<PlansResponseDTO> listPlans(Pageable pageable){
        Page<Plans> plans = plansRepository.findAll(pageable);
        Page<PlansResponseDTO> plansResponseDTOPageResponse = plans.map(plansMapper::fromEntity);
        return PageResponse.fromPage(plansResponseDTOPageResponse);
    }

    public void deletePlan(Long planId){
        Plans plans = findById(planId);
        plansRepository.delete(plans);
    }

    private Plans findById(Long planId){
        Plans plan = plansRepository.findById(planId)
                .orElseThrow(()->new NotFoundException("Plan Not Found"));
        return plan;
    }
}
