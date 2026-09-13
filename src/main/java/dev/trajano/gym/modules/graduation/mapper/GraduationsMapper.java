package dev.trajano.gym.modules.graduation.mapper;

import dev.trajano.gym.modules.graduation.domain.Graduations;
import dev.trajano.gym.modules.graduation.dto.GraduationsRequestDTO;
import dev.trajano.gym.modules.graduation.dto.GraduationsResponseDTO;
import dev.trajano.gym.modules.modality.domain.Modalities;
import org.springframework.stereotype.Component;

@Component
public class GraduationsMapper {
    public Graduations toEntity(Modalities modalities, GraduationsRequestDTO request){
        Graduations graduations = new Graduations();
        graduations.setName(request.name());
        graduations.setModalityId(modalities);
        return graduations;
    }

    public GraduationsResponseDTO fromEntity(Graduations graduations){
        return new GraduationsResponseDTO(
                graduations.getId(),
                graduations.getModalityId().getName(),
                graduations.getName()
        );
    }
}
