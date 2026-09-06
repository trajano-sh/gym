package dev.trajano.gym.modules.enrollmentinvoice.service;

import dev.trajano.gym.core.exception.NotFoundException;
import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.enrollmentinvoice.dto.EnrollmentInvoiceRequestDTO;
import dev.trajano.gym.modules.enrollmentinvoice.dto.EnrollmentInvoiceResponseDTO;
import dev.trajano.gym.modules.enrollmentinvoice.mapper.EnrollmentInvoiceMapper;
import dev.trajano.gym.modules.enrollmentinvoice.domain.EnrollmentInvoice;
import dev.trajano.gym.modules.enrollmentinvoice.repository.EnrollmentInvoiceRepository;
import dev.trajano.gym.modules.enrollment.domain.Enrollment;
import dev.trajano.gym.modules.enrollment.repository.EnrollmentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentInvoiceService {
    private final EnrollmentInvoiceRepository enrollmentInvoiceRepository;
    private final EnrollmentInvoiceMapper enrollmentInvoiceMapper;
    private final EnrollmentsRepository enrollmentsRepository;

    public EnrollmentInvoiceResponseDTO createEnrollmentInvoices(EnrollmentInvoiceRequestDTO requestDTO) {
        Enrollment enrollments = enrollmentsRepository.findById(requestDTO.enrollmentId())
                .orElseThrow(() -> new NotFoundException("Enrollments Not Found"));
        EnrollmentInvoice enrollmentInvoice = enrollmentInvoiceMapper.toEntity(requestDTO, enrollments);
        EnrollmentInvoice enrollmentInvoiceSave = enrollmentInvoiceRepository.save(enrollmentInvoice);
        return enrollmentInvoiceMapper.fromEntity(enrollmentInvoiceSave);
    }

    public PageResponse<EnrollmentInvoiceResponseDTO> listEnrollmentInvoices(Pageable pageable){
        Page<EnrollmentInvoice> enrollmentInvoices = enrollmentInvoiceRepository.findAll(pageable);
        Page<EnrollmentInvoiceResponseDTO> enrollmentInvoicesResponseDTOS = enrollmentInvoices.map(enrollmentInvoiceMapper::fromEntity);
        return PageResponse.fromPage(enrollmentInvoicesResponseDTOS);
    }

    public EnrollmentInvoiceResponseDTO searchEnrollmentInvoicesById(Long enrollmentInvoicesId){
        EnrollmentInvoice enrollmentInvoice = findById(enrollmentInvoicesId);
        return enrollmentInvoiceMapper.fromEntity(enrollmentInvoice);
    }

    public void deleteEnrollmentInvoices(Long enrollmentInvoicesId){
        EnrollmentInvoice enrollmentInvoice = findById(enrollmentInvoicesId);
        enrollmentInvoiceRepository.delete(enrollmentInvoice);
    }

    private EnrollmentInvoice findById(Long enrollmentInvoicesId) {
        EnrollmentInvoice enrollmentInvoice = enrollmentInvoiceRepository.findById(enrollmentInvoicesId)
                .orElseThrow(() -> new NotFoundException("EnrollmentInvoices Not Found"));
        return enrollmentInvoice;
    }
}
