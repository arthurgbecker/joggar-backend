package com.senai.JOGGAR.entities;

import java.sql.Date;
import java.util.List;

import com.senai.JOGGAR.dtos.UsuarioInputDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)

public class Usuario {
    public Usuario(UsuarioInputDTO dto) {
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Date nascimento;
    private Character generoUsuario;
    private String email;
    private String senha;
    private String telefone;

    @ManyToMany
    private List<Evento> eventos;

    @ManyToMany
    private List<Usuario> amigos;
}