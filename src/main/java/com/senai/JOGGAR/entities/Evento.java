package com.senai.JOGGAR.entities;

import java.sql.Date;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private String titulo;
    private String imagemDeCapa;
    private Date data;
    private Calendar hora;
    private String plataforma;
    private String privacidade;
    private String descricao;
    private Character generoEvento;

    @ManyToOne
    private Endereco endereco;

    @ManyToMany(mappedBy="eventos")
    private List<Usuario> usuarios;

    @Enumerated(EnumType.STRING)
    private Atividade atividade;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

}
