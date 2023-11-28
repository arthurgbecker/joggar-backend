package com.senai.JOGGAR.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UsuarioInputDTO{
    private Long id;
    private String nome;
    private String nascimento;
    private String generoUsuario;
    private String email;
    private String senha;
    private String telefone;
    private Boolean isAdmin;
    
}