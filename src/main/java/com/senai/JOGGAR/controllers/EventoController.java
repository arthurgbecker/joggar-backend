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

import com.senai.JOGGAR.dtos.EventoInputDTO;
import com.senai.JOGGAR.entities.Evento;
import com.senai.JOGGAR.services.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService service;

    @PostMapping
    public ResponseEntity<Evento> post(@RequestBody EventoInputDTO evento){
        Evento eventoCriado =  service.create(evento);
        return new ResponseEntity<Evento>(eventoCriado, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Evento> put(@RequestBody Evento evento){
        Evento eventoAtualizado = service.update(evento);
        return ResponseEntity.ok(eventoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Evento>> getList(){
        List<Evento> lista = service.list();
        return ResponseEntity.ok(lista);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getRead(@PathVariable Long id){
        Evento eventoEncontrado = service.read(id);
        return ResponseEntity.ok(eventoEncontrado);
    }
}