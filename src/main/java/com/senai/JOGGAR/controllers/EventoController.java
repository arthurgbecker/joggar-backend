package com.senai.JOGGAR.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.JOGGAR.dtos.EventoInputDTO;
import com.senai.JOGGAR.dtos.EventoOutputDTO;
import com.senai.JOGGAR.services.EventoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private EventoService service;

    @PostMapping
    public ResponseEntity<EventoOutputDTO> post(@RequestBody @Valid EventoInputDTO evento) {
        EventoOutputDTO eventoCriado = service.create(evento);
        return new ResponseEntity<EventoOutputDTO>(eventoCriado, HttpStatus.CREATED);
    }

    @PutMapping(value = "/eventos/{id}")
    public ResponseEntity<EventoOutputDTO> put(@PathVariable Long id,
            @RequestBody EventoInputDTO evento) {
        EventoOutputDTO eventoAtualizado = service.update(evento);
        return ResponseEntity.ok(eventoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EventoOutputDTO>> getList() {
        List<EventoOutputDTO> lista = service.list();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoOutputDTO> getRead(@PathVariable Long id) {
        EventoOutputDTO eventoEncontrado = service.read(id);
        return ResponseEntity.ok(eventoEncontrado);
    }
}