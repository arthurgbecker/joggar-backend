package com.senai.JOGGAR.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {
    @Value("${api.secret}")
    private String secret;

    @Value("${api.issuer}")
    private String issuer;

    @Value("${api.expirationMinutes}")
    private String expirationMinutes;

    public String generateToken(UserDetails usuario) {
        var algorithm = Algorithm.HMAC256(secret);
        var token = JWT
                .create()
                .withExpiresAt(LocalDateTime.now().plusMinutes(Long.parseLong(expirationMinutes))
                        .toInstant(ZoneOffset.of("-03:00")))
                .withSubject(usuario.getUsername())
                .withIssuer(issuer)
                .sign(algorithm);
        return token;
    }

    public String getEmail(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT
                    .require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido ou expirado.");
        }
    }
}
