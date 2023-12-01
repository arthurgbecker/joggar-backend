package com.senai.JOGGAR.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.senai.JOGGAR.dtos.UsuarioInputDTO;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor

public class Usuario implements UserDetails{

    public Usuario(UsuarioInputDTO dto) {
        this.nome = dto.getNome();
        this.nascimento = dto.getNascimento();
        this.generoUsuario = dto.getGeneroUsuario();
        this.email = dto.getEmail();
        this.senha = dto.getSenha();
        this.telefone = dto.getTelefone();
        this.isAdmin = dto.getIsAdmin();
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String nascimento;
    private String generoUsuario;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;
    private String telefone;
    private Boolean isAdmin;

    @ManyToMany
    private List<Evento> eventos;

    @ManyToMany
    private List<Usuario> amigos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.isAdmin != null && this.isAdmin == true){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else{
            return null;
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}