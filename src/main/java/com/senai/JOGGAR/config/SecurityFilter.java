package com.senai.JOGGAR.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.senai.JOGGAR.repositories.UsuarioRepository;
import com.senai.JOGGAR.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService service;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain) 
            throws IOException, ServletException{
        var token = getToken(request);

        if(token != null){
            var email = service.getEmail(token);
            var user = repository.findByEmail(email);
            var login = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getSenha()
            );
            SecurityContextHolder.getContext().setAuthentication(login);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken (HttpServletRequest request){
        var authorization = request.getHeader("Authorization");
        if(authorization != null){
        return authorization.replace("Bearer", "");
        }
        return null;
    }
}
