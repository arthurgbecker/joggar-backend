package com.senai.JOGGAR.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String local;
    
    @OneToMany(mappedBy="endereco")
    private List<Evento> evento;
}
