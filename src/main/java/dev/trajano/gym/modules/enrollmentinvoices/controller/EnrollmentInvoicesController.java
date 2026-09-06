package dev.trajano.gym.modules.enrollmentinvoices.controller;

import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.enrollmentinvoices.dto.EnrollmentInvoicesRequestDTO;
import dev.trajano.gym.modules.enrollmentinvoices.dto.EnrollmentInvoicesResponseDTO;
import dev.trajano.gym.modules.enrollmentinvoices.service.EnrollmentInvoicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollment-invoices")
public class EnrollmentInvoicesController {
    private final EnrollmentInvoicesService enrollmentInvoicesService;

    @PostMapping
    public ResponseEntity<EnrollmentInvoicesResponseDTO> createEnrollmentInvoices(@RequestBody EnrollmentInvoicesRequestDTO requestDTO) {
        return ResponseEntity.ok(enrollmentInvoicesService.createEnrollmentInvoices(requestDTO));
    }

    @GetMapping("/{enrollmentInvoicesId}")
    public ResponseEntity<EnrollmentInvoicesResponseDTO> searchEnrollmentInvoicesById(@PathVariable Long enrollmentInvoicesId) {
        return ResponseEntity.ok(enrollmentInvoicesService.searchEnrollmentInvoicesById(enrollmentInvoicesId));
    }
    @GetMapping
    public ResponseEntity<PageResponse<EnrollmentInvoicesResponseDTO>> listEnrollmentInvoices(Pageable pageable){
        return ResponseEntity.ok(enrollmentInvoicesService.listEnrollmentInvoices(pageable));
    }

    @DeleteMapping("/{enrollmentInvoicesId}")
    public ResponseEntity<Void> deleteEnrollmentInvoices(@PathVariable Long enrollmentInvoicesId) {
        enrollmentInvoicesService.deleteEnrollmentInvoices(enrollmentInvoicesId);
        return ResponseEntity.noContent().build();
    }

}
