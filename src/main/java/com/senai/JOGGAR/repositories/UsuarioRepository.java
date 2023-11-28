package com.senai.JOGGAR.repositories;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.JOGGAR.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Usuario findByEmail(String email);
    // Optional<Usuario> findByEmail(String email);
    
}
