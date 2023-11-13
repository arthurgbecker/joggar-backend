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

import com.senai.JOGGAR.entities.Preferencia;
import com.senai.JOGGAR.services.PreferenciaService;

@RestController
@RequestMapping("/preferencias")
public class PreferenciaController {

    @Autowired
    private PreferenciaService service;

    @PostMapping
    public ResponseEntity<Preferencia> post(@RequestBody Preferencia preferencia){
        Preferencia preferenciaCriado =  service.create(preferencia);
        return new ResponseEntity<Preferencia>(preferenciaCriado, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Preferencia> put(@RequestBody Preferencia preferencia){
        Preferencia preferenciaAtualizado = service.update(preferencia);
        return ResponseEntity.ok(preferenciaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Preferencia>> getList(){
        List<Preferencia> lista = service.list();
        return ResponseEntity.ok(lista);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Preferencia> getRead(@PathVariable Long id){
        Preferencia preferenciaEncontrado = service.read(id);
        return ResponseEntity.ok(preferenciaEncontrado);
    }
}