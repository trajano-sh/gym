package dev.trajano.gym.modules.regularity.domain;

import dev.trajano.gym.modules.enrollments.domain.Enrollments;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "regularity")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Regularity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollments enrollmentsId;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime entryDate;

    private LocalDateTime exitDate;
}
