package com.senai.JOGGAR.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.senai.JOGGAR.dtos.LoginInputDTO;
import com.senai.JOGGAR.dtos.RefreshTokenDTO;
import com.senai.JOGGAR.dtos.TokenDTO;
import com.senai.JOGGAR.entities.Usuario;
import com.senai.JOGGAR.entities.RefreshToken;
import com.senai.JOGGAR.services.RefreshTokenService;
import com.senai.JOGGAR.services.TokenService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginInputDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                dados.getEmail(),
                dados.getSenha());
        var authentication = manager.authenticate(authenticationToken);
        var token = tokenService.gerarToken((UserDetails) authentication.getPrincipal());
        var user = (UserDetails) authentication.getPrincipal();
        var refreshToken = refreshTokenService.createRefreshToken(user, token);
        return new ResponseEntity<TokenDTO>(
                new TokenDTO(token, refreshToken.getRefreshToken(), user.getUsername()),
                HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDTO> refreshtoken(@Valid @RequestBody RefreshTokenDTO request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUsuario)
                .map(usuario -> getToken(requestRefreshToken, usuario))
                .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
    }

    private ResponseEntity<TokenDTO> getToken(String requestRefreshToken, Usuario usuario) {
        String token = tokenService.gerarToken(usuario);
        refreshTokenService.deleteByUser(usuario);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(usuario, token);
        return ResponseEntity.ok(new TokenDTO(token, refreshToken.getRefreshToken(), usuario.getEmail()));
    }

    @DeleteMapping("/revoke")
    public ResponseEntity<?> revokeToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var usuario = (Usuario) auth.getPrincipal();
        refreshTokenService.deleteByUser(usuario);
        return ResponseEntity.noContent().build();
    }

}
