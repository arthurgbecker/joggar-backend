package com.senai.JOGGAR.dtos;


import com.senai.JOGGAR.entities.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UsuarioOutputDTO{
    public UsuarioOutputDTO(Usuario usuario){
        id = usuario.getId();
        nome = usuario.getNome();
        nascimento = usuario.getNascimento();
        generoUsuario = usuario.getGeneroUsuario();
        email = usuario.getEmail();
        senha = usuario.getSenha();
        telefone = usuario.getTelefone();
        isAdmin = usuario.getIsAdmin();
    }
    private Long id;
    private String nome;
    private String nascimento;
    private String generoUsuario;
    private String email;
    private String senha;
    private String telefone;
    private Boolean isAdmin;
    
}
