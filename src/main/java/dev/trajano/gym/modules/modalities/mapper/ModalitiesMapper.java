package dev.trajano.gym.modules.modalities.mapper;

import dev.trajano.gym.modules.modalities.domain.Modalities;
import dev.trajano.gym.modules.modalities.dto.ModalitiesRequestDTO;
import dev.trajano.gym.modules.modalities.dto.ModalitiesResponseDTO;
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
