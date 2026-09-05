package dev.trajano.gym.modules.enrollments.model;

import dev.trajano.gym.modules.students.model.Students;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Table(name = "enrollments")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enrollments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Students studentId;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate enrollmentDate;

    private LocalDate dayMaturity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentsEnum status = EnrollmentsEnum.ACTIVE;
}
