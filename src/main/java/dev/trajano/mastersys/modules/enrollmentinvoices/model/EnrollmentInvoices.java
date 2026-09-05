package dev.trajano.mastersys.modules.enrollmentinvoices.model;

import dev.trajano.mastersys.modules.enrollments.model.Enrollments;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "enrollment_invoices",
        uniqueConstraints = @UniqueConstraint(columnNames = {"enrollment_id", "due_date"}))
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentInvoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollments enrollmentId;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(name = "value", nullable = false, precision = 10, scale = 2, columnDefinition = "NUMERIC(10, 2) CHECK (value >= 0)")
    private BigDecimal value;

    private LocalDateTime paymentDate;

    private LocalDate cancellationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentInvoicesEnum status = EnrollmentInvoicesEnum.OPEN;
}
