package dev.trajano.gym.modules.enrollmentsmodalities.model;

import dev.trajano.gym.modules.enrollments.model.Enrollments;
import dev.trajano.gym.modules.graduations.model.Graduations;
import dev.trajano.gym.modules.modalities.model.Modalities;
import dev.trajano.gym.modules.plans.model.Plans;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Table(name = "enrollments_modalities",
        uniqueConstraints = @UniqueConstraint(columnNames = {"enrollment_id", "graduation_id"}))
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentsModalities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollments enrollmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modality_id", nullable = false)
    private Modalities modalityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graduation_id", nullable = false)
    private Graduations graduationsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plans planId;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate startDate;

    private LocalDate endDate;
}
