package dev.trajano.gym.modules.plans.mapper;

import dev.trajano.gym.modules.modalities.domain.Modalities;
import dev.trajano.gym.modules.plans.dto.PlansRequestDTO;
import dev.trajano.gym.modules.plans.dto.PlansResponseDTO;
import dev.trajano.gym.modules.plans.domain.Plans;
import org.springframework.stereotype.Component;

@Component
public class PlansMapper {
    public Plans toEntity(Modalities modality, PlansRequestDTO requestDTO){
        Plans plans = new Plans();
        plans.setModalityId(modality);
        plans.setName(requestDTO.name());
        plans.setMonthlyAmount(requestDTO.monthlyAmount());
        return plans;
    }

    public PlansResponseDTO fromEntity(Plans plans){
        return new PlansResponseDTO(
                plans.getId(),
                plans.getModalityId().getId(),
                plans.getName(),
                plans.getMonthlyAmount(),
                plans.getActive()
        );
    }
}
