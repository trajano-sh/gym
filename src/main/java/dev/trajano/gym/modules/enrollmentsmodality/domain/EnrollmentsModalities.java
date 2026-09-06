package dev.trajano.gym.modules.enrollmentsmodality.domain;

import dev.trajano.gym.modules.enrollment.domain.Enrollment;
import dev.trajano.gym.modules.graduation.domain.Graduations;
import dev.trajano.gym.modules.modality.domain.Modalities;
import dev.trajano.gym.modules.plan.domain.Plan;
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
    private Enrollment enrollmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modality_id", nullable = false)
    private Modalities modalityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graduation_id", nullable = false)
    private Graduations graduationsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan planId;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate startDate;

    private LocalDate endDate;
}
