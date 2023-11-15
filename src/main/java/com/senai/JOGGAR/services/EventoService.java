package com.senai.JOGGAR.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.JOGGAR.dtos.EventoInputDTO;
import com.senai.JOGGAR.entities.Evento;
import com.senai.JOGGAR.repositories.EventoRepository;

@Service
public class EventoService {
    @Autowired
    private EventoRepository repository;

    @Transactional
    public Evento create(EventoInputDTO dto){
        Evento evento = new Evento(dto);
        Evento eventoCriado = repository.save(evento);
        return eventoCriado;
    }

    public Evento read(Long id){
        return repository.findById(id).get();
    }

    public List<Evento> list(){
        List<Evento> eventos = (List<Evento>) repository.findAll(/*Example.of(EVENTO CRIADO PELO JSON VIA BUSCA)*/);
        return eventos;
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public Evento update(Evento evento){
        if(repository.existsById(evento.getId())){
            return repository.save(evento);
        }else{
            return null;
        }
    }
}