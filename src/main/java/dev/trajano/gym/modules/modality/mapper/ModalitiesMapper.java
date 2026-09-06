package dev.trajano.gym.modules.modality.mapper;

import dev.trajano.gym.modules.modality.domain.Modalities;
import dev.trajano.gym.modules.modality.dto.ModalitiesRequestDTO;
import dev.trajano.gym.modules.modality.dto.ModalitiesResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ModalitiesMapper {
    public Modalities toEntity(ModalitiesRequestDTO requestDTO) {
        Modalities modalities = new Modalities();
        modalities.setName(requestDTO.name());
        return modalities;
    }

    public ModalitiesResponseDTO fromEntity(Modalities modalities){
        return new ModalitiesResponseDTO(
                modalities.getId(),
                modalities.getName(),
                modalities.getActive()
        );
    }
}
