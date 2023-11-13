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

import com.senai.JOGGAR.entities.Endereco;
import com.senai.JOGGAR.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping
    public ResponseEntity<Endereco> post(@RequestBody Endereco endereco){
        Endereco enderecoCriado =  service.create(endereco);
        return new ResponseEntity<Endereco>(enderecoCriado, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Endereco> put(@RequestBody Endereco endereco){
        Endereco enderecoAtualizado = service.update(endereco);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> getList(){
        List<Endereco> lista = service.list();
        return ResponseEntity.ok(lista);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getRead(@PathVariable Long id){
        Endereco enderecoEncontrado = service.read(id);
        return ResponseEntity.ok(enderecoEncontrado);
    }
}