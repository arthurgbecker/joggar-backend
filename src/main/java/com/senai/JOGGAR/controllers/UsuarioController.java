package com.senai.JOGGAR.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.JOGGAR.dtos.UsuarioInputDTO;
import com.senai.JOGGAR.entities.Usuario;
import com.senai.JOGGAR.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> post(@RequestBody UsuarioInputDTO usuario){
        Usuario usuarioCriado =  service.create(usuario, false);
        return new ResponseEntity<Usuario>(usuarioCriado, HttpStatus.CREATED);
    }

    @PostMapping("/admin")
    public ResponseEntity<Usuario> postAdmin(@RequestBody UsuarioInputDTO usuario){
        Usuario usuarioCriado =  service.create(usuario, true);
        return new ResponseEntity<Usuario>(usuarioCriado, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Usuario> put(@RequestBody Usuario usuario){
        Usuario usuarioAtualizado = service.update(usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getList(){
        List<Usuario> lista = service.list();
        return ResponseEntity.ok(lista);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getRead(@PathVariable Long id){
        Usuario usuarioEncontrado = service.read(id);
        return ResponseEntity.ok(usuarioEncontrado);
    }
}