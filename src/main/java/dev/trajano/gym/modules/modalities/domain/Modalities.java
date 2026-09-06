package dev.trajano.gym.modules.modalities.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "modalities")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Modalities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean active = Boolean.TRUE;
}
