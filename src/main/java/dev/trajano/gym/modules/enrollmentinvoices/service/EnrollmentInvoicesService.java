package dev.trajano.gym.modules.enrollmentinvoices.service;

import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.enrollmentinvoices.dto.EnrollmentInvoicesRequestDTO;
import dev.trajano.gym.modules.enrollmentinvoices.dto.EnrollmentInvoicesResponseDTO;
import dev.trajano.gym.modules.enrollmentinvoices.mapper.EnrollmentInvoicesMapper;
import dev.trajano.gym.modules.enrollmentinvoices.domain.EnrollmentInvoices;
import dev.trajano.gym.modules.enrollmentinvoices.repository.EnrollmentInvoicesRepository;
import dev.trajano.gym.modules.enrollments.domain.Enrollments;
import dev.trajano.gym.modules.enrollments.repository.EnrollmentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentInvoicesService {
    private final EnrollmentInvoicesRepository enrollmentInvoicesRepository;
    private final EnrollmentInvoicesMapper enrollmentInvoicesMapper;
    private final EnrollmentsRepository enrollmentsRepository;

    public EnrollmentInvoicesResponseDTO createEnrollmentInvoices(EnrollmentInvoicesRequestDTO requestDTO) {
        Enrollments enrollments = enrollmentsRepository.findById(requestDTO.enrollmentId())
                .orElseThrow(() -> new NotFoundException("Enrollments Not Found"));
        EnrollmentInvoices enrollmentInvoices = enrollmentInvoicesMapper.toEntity(requestDTO, enrollments);
        EnrollmentInvoices enrollmentInvoicesSave = enrollmentInvoicesRepository.save(enrollmentInvoices);
        return enrollmentInvoicesMapper.fromEntity(enrollmentInvoicesSave);
    }

    public PageResponse<EnrollmentInvoicesResponseDTO> listEnrollmentInvoices(Pageable pageable){
        Page<EnrollmentInvoices> enrollmentInvoices = enrollmentInvoicesRepository.findAll(pageable);
        Page<EnrollmentInvoicesResponseDTO> enrollmentInvoicesResponseDTOS = enrollmentInvoices.map(enrollmentInvoicesMapper::fromEntity);
        return PageResponse.fromPage(enrollmentInvoicesResponseDTOS);
    }

    public EnrollmentInvoicesResponseDTO searchEnrollmentInvoicesById(Long enrollmentInvoicesId){
        EnrollmentInvoices enrollmentInvoices = findById(enrollmentInvoicesId);
        return enrollmentInvoicesMapper.fromEntity(enrollmentInvoices);
    }

    public void deleteEnrollmentInvoices(Long enrollmentInvoicesId){
        EnrollmentInvoices enrollmentInvoices = findById(enrollmentInvoicesId);
        enrollmentInvoicesRepository.delete(enrollmentInvoices);
    }

    private EnrollmentInvoices findById(Long enrollmentInvoicesId) {
        EnrollmentInvoices enrollmentInvoices = enrollmentInvoicesRepository.findById(enrollmentInvoicesId)
                .orElseThrow(() -> new NotFoundException("EnrollmentInvoices Not Found"));
        return enrollmentInvoices;
    }
}
