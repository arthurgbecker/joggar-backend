package com.senai.JOGGAR.entities;

import java.util.List;

import com.senai.JOGGAR.dtos.EventoInputDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity  
@Data @AllArgsConstructor @NoArgsConstructor 
public class Evento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tituloEvento;
    private String imagemEvento;
    private String dataEvento;
    private String horaEvento;
    private String privacidadeEvento;
    private String descricaoEvento;
    private String publicoEvento;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Endereco endereco;

    @ManyToMany(mappedBy="eventos")
    private List<Usuario> usuarios;

    @Enumerated(EnumType.STRING)
    private Atividade atividade;
    
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

}
