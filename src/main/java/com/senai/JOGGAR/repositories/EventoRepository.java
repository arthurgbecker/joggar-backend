package com.senai.JOGGAR.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.JOGGAR.entities.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    
}
