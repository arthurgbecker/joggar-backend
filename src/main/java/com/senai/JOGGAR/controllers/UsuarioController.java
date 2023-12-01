package com.senai.JOGGAR.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.senai.JOGGAR.dtos.UsuarioInputDTO;
import com.senai.JOGGAR.dtos.UsuarioOutputDTO;
import com.senai.JOGGAR.entities.Usuario;
import com.senai.JOGGAR.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioOutputDTO> post(@RequestBody @Valid UsuarioInputDTO usuario) {
        UsuarioOutputDTO usuarioCriado = service.create(usuario);
        return new ResponseEntity<UsuarioOutputDTO>(usuarioCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioOutputDTO> put(@PathVariable Long id, @RequestBody UsuarioInputDTO usuario) {
        UsuarioOutputDTO usuarioAtualizado = service.update(usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioOutputDTO>> getList() {
        List<UsuarioOutputDTO> lista = service.list();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioOutputDTO> getRead(@PathVariable Long id) {
        UsuarioOutputDTO usuarioEncontrado = service.read(id);
        return ResponseEntity.ok(usuarioEncontrado);
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioOutputDTO> getMe() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var usuario = (Usuario) auth.getPrincipal();
        var out = new UsuarioOutputDTO(usuario);
        return ResponseEntity.ok(out);
    }
}