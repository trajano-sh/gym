package dev.trajano.mastersys.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "graduations")
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
    @JoinColumn(name = "modality_id", unique = true, referencedColumnName = "id", nullable = false)
    private Modalities modalityId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;
}