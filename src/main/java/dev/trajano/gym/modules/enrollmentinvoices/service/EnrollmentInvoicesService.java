package dev.trajano.gym.modules.enrollmentinvoices.service;

import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.modules.enrollmentinvoices.dto.EnrollmentInvoicesRequestDTO;
import dev.trajano.gym.modules.enrollmentinvoices.dto.EnrollmentInvoicesResponseDTO;
import dev.trajano.gym.modules.enrollmentinvoices.mapper.EnrollmentInvoicesMapper;
import dev.trajano.gym.modules.enrollmentinvoices.model.EnrollmentInvoices;
import dev.trajano.gym.modules.enrollmentinvoices.repository.EnrollmentInvoicesRepository;
import dev.trajano.gym.modules.enrollments.model.Enrollments;
import dev.trajano.gym.modules.enrollments.repository.EnrollmentsRepository;
import dev.trajano.gym.modules.enrollmentsmodalities.model.EnrollmentsModalities;
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

    public EnrollmentInvoicesResponseDTO register(Long enrollmentId, EnrollmentInvoicesRequestDTO requestDTO) {
        Enrollments enrollments = enrollmentsRepository.findById(enrollmentId)
                .orElseThrow(() -> new NotFoundException("Enrollments Not Found"));
        EnrollmentInvoices enrollmentInvoices = enrollmentInvoicesMapper.toEntity(requestDTO, enrollments);
        EnrollmentInvoices enrollmentInvoicesSave = enrollmentInvoicesRepository.save(enrollmentInvoices);
        return enrollmentInvoicesMapper.fromEntity(enrollmentInvoicesSave);
    }

    public Page<EnrollmentInvoicesResponseDTO> list(Pageable pageable){
        return enrollmentInvoicesRepository.findAll(pageable).map(enrollmentInvoicesMapper::fromEntity);
    }

    public EnrollmentInvoicesResponseDTO findEnrollmentInvoicesById(Long enrollmentInvoicesId){
        EnrollmentInvoices enrollmentInvoices = findById(enrollmentInvoicesId);
        return enrollmentInvoicesMapper.fromEntity(enrollmentInvoices);
    }

    public void delete(Long id){
        EnrollmentInvoices enrollmentInvoices = findById(id);
        enrollmentInvoicesRepository.delete(enrollmentInvoices);
    }

    private EnrollmentInvoices findById(Long enrollmentInvoicesId) {
        EnrollmentInvoices enrollmentInvoices = enrollmentInvoicesRepository.findById(enrollmentInvoicesId)
                .orElseThrow(() -> new NotFoundException("EnrollmentInvoices Not Found"));
        return enrollmentInvoices;
    }
}
