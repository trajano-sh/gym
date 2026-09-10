package dev.trajano.gym.modules.graduation.service;

import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.graduation.domain.Graduations;
import dev.trajano.gym.modules.graduation.dto.GraduationsRequestDTO;
import dev.trajano.gym.modules.graduation.dto.GraduationsResponseDTO;
import dev.trajano.gym.modules.graduation.mapper.GraduationsMapper;
import dev.trajano.gym.modules.graduation.repository.GraduationsRepository;
import dev.trajano.gym.modules.modality.domain.Modalities;
import dev.trajano.gym.modules.modality.service.ModalitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GraduationsService {
    private final GraduationsMapper graduationsMapper;
    private final GraduationsRepository graduationsRepository;
    private final ModalitiesService modalitiesService;

    public GraduationsResponseDTO createGraduation(GraduationsRequestDTO requestDTO) {
        Modalities modalities = modalitiesService.findById(requestDTO.modalityId());
        Graduations graduations = graduationsMapper.toEntity(modalities, requestDTO);
        graduationsRepository.save(graduations);
        return graduationsMapper.fromEntity(graduations);
    }

    public PageResponse<GraduationsResponseDTO> listGraduations(Pageable pageable) {
        Page<Graduations> graduations = graduationsRepository.findAll(pageable);
        Page<GraduationsResponseDTO> graduationsResponseDTOS = graduations.map(graduationsMapper::fromEntity);
        return PageResponse.fromPage(graduationsResponseDTOS);
    }

    public GraduationsResponseDTO findGraduationResponse(Long graduationId) {
        Graduations graduations = findById(graduationId);
        return graduationsMapper.fromEntity(graduations);
    }

    public void deleteGraduation(Long graduationId) {
        Graduations graduations = findById(graduationId);
        graduationsRepository.delete(graduations);
    }

    private Graduations findById(Long graduationId) {
        Graduations graduations = graduationsRepository.findById(graduationId).orElseThrow(() -> new NotFoundException("Graduations Not Found"));
        return graduations;
    }
}
