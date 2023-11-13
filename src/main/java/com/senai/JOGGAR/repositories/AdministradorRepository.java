package com.senai.JOGGAR.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.JOGGAR.entities.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    
}
