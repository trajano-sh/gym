package dev.trajano.gym.modules.plan.mapper;

import dev.trajano.gym.modules.modality.domain.Modalities;
import dev.trajano.gym.modules.plan.domain.Plan;
import dev.trajano.gym.modules.plan.dto.PlanRequestDTO;
import dev.trajano.gym.modules.plan.dto.PlanResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {
    public Plan toEntity(Modalities modality, PlanRequestDTO requestDTO){
        Plan plan = new Plan();
        plan.setModalityId(modality);
        plan.setName(requestDTO.name());
        plan.setMonthlyAmount(requestDTO.monthlyAmount());
        return plan;
    }

    public PlanResponseDTO fromEntity(Plan plan){
        return new PlanResponseDTO(
                plan.getId(),
                plan.getModalityId().getId(),
                plan.getName(),
                plan.getMonthlyAmount(),
                plan.getActive()
        );
    }
}
