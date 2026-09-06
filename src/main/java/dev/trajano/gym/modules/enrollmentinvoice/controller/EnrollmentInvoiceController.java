package dev.trajano.gym.modules.enrollmentinvoice.controller;

import dev.trajano.gym.core.utils.PageResponse;
import dev.trajano.gym.modules.enrollmentinvoice.dto.EnrollmentInvoiceRequestDTO;
import dev.trajano.gym.modules.enrollmentinvoice.dto.EnrollmentInvoiceResponseDTO;
import dev.trajano.gym.modules.enrollmentinvoice.service.EnrollmentInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollment-invoices")
public class EnrollmentInvoiceController {
    private final EnrollmentInvoiceService enrollmentInvoiceService;

    @PostMapping
    public ResponseEntity<EnrollmentInvoiceResponseDTO> createEnrollmentInvoices(@RequestBody EnrollmentInvoiceRequestDTO requestDTO) {
        return ResponseEntity.ok(enrollmentInvoiceService.createEnrollmentInvoices(requestDTO));
    }

    @GetMapping("/{enrollmentInvoicesId}")
    public ResponseEntity<EnrollmentInvoiceResponseDTO> searchEnrollmentInvoicesById(@PathVariable Long enrollmentInvoicesId) {
        return ResponseEntity.ok(enrollmentInvoiceService.searchEnrollmentInvoicesById(enrollmentInvoicesId));
    }
    @GetMapping
    public ResponseEntity<PageResponse<EnrollmentInvoiceResponseDTO>> listEnrollmentInvoices(Pageable pageable){
        return ResponseEntity.ok(enrollmentInvoiceService.listEnrollmentInvoices(pageable));
    }

    @DeleteMapping("/{enrollmentInvoicesId}")
    public ResponseEntity<Void> deleteEnrollmentInvoices(@PathVariable Long enrollmentInvoicesId) {
        enrollmentInvoiceService.deleteEnrollmentInvoices(enrollmentInvoicesId);
        return ResponseEntity.noContent().build();
    }

}
