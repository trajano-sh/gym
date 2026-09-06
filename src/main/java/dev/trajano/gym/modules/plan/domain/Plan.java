package dev.trajano.gym.modules.plan.domain;

import dev.trajano.gym.modules.modality.domain.Modalities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "plans",
        uniqueConstraints = @UniqueConstraint(columnNames = {"modality_id", "name"}))
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modality_id", nullable = false)
    private Modalities modalityId;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "monthly_amount", nullable = false, precision = 10, scale = 2, columnDefinition = "NUMERIC(10, 2) CHECK (monthly_amount >= 0)")
    private BigDecimal monthlyAmount;

    @Column(nullable = false)
    private Boolean active = Boolean.TRUE;
}
