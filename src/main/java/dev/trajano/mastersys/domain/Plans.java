package dev.trajano.mastersys.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "plans")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modality_id", columnDefinition = "id", unique = true)
    private Modalities modalityId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "monthly_amount", nullable = false, precision = 10, scale = 2, columnDefinition = "NUMERIC(10, 2) CHECK (monthly_amount >= 0)")
    private BigDecimal monthlyAmount;

    @Column(nullable = false)
    private Boolean active;
}
