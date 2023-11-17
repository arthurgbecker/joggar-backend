package com.senai.JOGGAR.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.JOGGAR.dtos.EventoInputDTO;
import com.senai.JOGGAR.dtos.EventoOutputDTO;
import com.senai.JOGGAR.entities.Evento;
import com.senai.JOGGAR.repositories.EventoRepository;

@Service
public class EventoService {
    @Autowired
    private EventoRepository repository;

    @Transactional
    public EventoOutputDTO create(EventoInputDTO dto){
        Evento eventoCriado = repository.save(converterDtoParaEntidade(dto));
        return converterEntidadeParaDTO(eventoCriado);
    }

    public EventoOutputDTO converterEntidadeParaDTO(Evento evento){
        EventoOutputDTO dtoSaida = new EventoOutputDTO();
        dtoSaida.setId(evento.getId());
        dtoSaida.setTituloEvento(evento.getTituloEvento());
        dtoSaida.setImagemEvento(evento.getImagemEvento());
        dtoSaida.setDataEvento(evento.getDataEvento());
        dtoSaida.setHoraEvento(evento.getHoraEvento());
        dtoSaida.setPrivacidadeEvento(evento.getPrivacidadeEvento());
        dtoSaida.setEndereco(evento.getEndereco());
        dtoSaida.setDescricaoEvento(evento.getDescricaoEvento());
        dtoSaida.setAtividade(evento.getAtividade());
        dtoSaida.setPublicoEvento(evento.getPublicoEvento());
        dtoSaida.setTipo(evento.getTipo());
        return dtoSaida;
    }

    public Evento converterDtoParaEntidade(EventoInputDTO dto){
        Evento evento = new Evento();
        evento.setTituloEvento(dto.getTituloEvento());
        evento.setImagemEvento(dto.getImagemEvento());
        evento.setDataEvento(dto.getDataEvento());
        evento.setHoraEvento(dto.getHoraEvento());
        evento.setPrivacidadeEvento(dto.getPrivacidadeEvento());
        evento.setEndereco(dto.getEndereco());
        evento.setDescricaoEvento(dto.getDescricaoEvento());
        evento.setAtividade(dto.getAtividade());
        evento.setPublicoEvento(dto.getPublicoEvento());
        evento.setTipo(dto.getTipo());
        return evento;
    }

    public EventoOutputDTO read(Long id){
        Evento evento = repository.findById(id).get();
        return converterEntidadeParaDTO(evento);
    }

    public List<EventoOutputDTO> list(Pageable page){
        return repository.findAll(page).stream().map(p->converterEntidadeParaDTO(p)).toList();
        // List<Evento> eventos = (List<Evento>) repository.findAll(/*Example.of(EVENTO CRIADO PELO JSON VIA BUSCA)*/);
        // return eventos;
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public EventoOutputDTO update(EventoInputDTO evento){
        if(evento.getId() == null){
            evento.setId(99l);
        }
        if(repository.existsById(evento.getId())){
            Evento eventoAtualizado = repository.save(converterDtoParaEntidade(evento));
            return converterEntidadeParaDTO(eventoAtualizado);
        }else{
            return null;
        }
    }
}