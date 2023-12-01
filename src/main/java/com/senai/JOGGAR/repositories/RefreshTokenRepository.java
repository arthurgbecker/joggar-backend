package com.senai.JOGGAR.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.senai.JOGGAR.entities.RefreshToken;
import com.senai.JOGGAR.entities.Usuario;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
   Optional<RefreshToken> findByToken(String token);
   Optional<RefreshToken> findByRefreshToken(String refreshToken);
   @Modifying
   int deleteByUsuario(Usuario usuario);
}