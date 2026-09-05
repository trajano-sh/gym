package dev.trajano.mastersys.modules.graduations.model;

import dev.trajano.mastersys.modules.modalities.model.Modalities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "graduations",
        uniqueConstraints = @UniqueConstraint(columnNames = {"modality_id", "name"}))
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Graduations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modality_id", nullable = false)
    private Modalities modalityId;

    @Column(length = 100, nullable = false)
    private String name;
}