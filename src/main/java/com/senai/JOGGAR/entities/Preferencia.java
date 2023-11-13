package com.senai.JOGGAR.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor

public class Preferencia {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Character generoEvento;
    private String localEvento;

    @OneToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Atividade atividade;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
}
