package dev.trajano.gym.modules.modalities.service;

import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.modalities.domain.Modalities;
import dev.trajano.gym.modules.modalities.dto.ModalitiesRequestDTO;
import dev.trajano.gym.modules.modalities.dto.ModalitiesResponseDTO;
import dev.trajano.gym.modules.modalities.mapper.ModalitiesMapper;
import dev.trajano.gym.modules.modalities.repository.ModalitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModalitiesService {
    private final ModalitiesRepository modalitiesRepository;
    private final ModalitiesMapper modalitiesMapper;

    public ModalitiesResponseDTO createModality(ModalitiesRequestDTO dto) {
        Modalities modality = modalitiesMapper.toEntity(dto);
        modalitiesRepository.save(modality);
        return modalitiesMapper.fromEntity(modality);
    }

    public ModalitiesResponseDTO searchModalityById(Long modalityId) {
        Modalities modality = findById(modalityId);
        return modalitiesMapper.fromEntity(modality);
    }

    public PageResponse<ModalitiesResponseDTO> listModalities(Pageable pageable){
        Page<Modalities> modalities = modalitiesRepository.findAll(pageable);
        Page<ModalitiesResponseDTO> modalitiesResponseDTOS = modalities.map(modalitiesMapper::fromEntity);
        return PageResponse.fromPage(modalitiesResponseDTOS);
    }

    public void deleteModalities(Long modalityId){
        Modalities modalities = findById(modalityId);
        modalitiesRepository.delete(modalities);
    }

    private Modalities findById(Long modalityId) {
        Modalities modalities = modalitiesRepository.findById(modalityId).orElseThrow(() -> new NotFoundException("Modality Not Found"));
        return modalities;
    }
}
