package com.senai.JOGGAR.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.JOGGAR.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
