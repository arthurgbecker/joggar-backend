package com.senai.JOGGAR.entities;

import java.sql.Date;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
import java.util.List;

import com.senai.JOGGAR.dtos.EventoInputDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity  
@Data @AllArgsConstructor @NoArgsConstructor 
public class Evento {
    public Evento(EventoInputDTO dto) {
    }
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tituloEvento;
    private String imagemEvento;
    private Date dataEvento;
    private String horaEvento;
    private String privacidadeEvento;
    private String descricaoEvento;
    private String publicoEvento;

    @ManyToOne
    private Endereco endereco;

    @ManyToMany(mappedBy="eventos")
    private List<Usuario> usuarios;

    @Enumerated(EnumType.STRING)
    private Atividade atividade;
    
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

}
