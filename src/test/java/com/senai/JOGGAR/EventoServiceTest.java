package com.senai.JOGGAR;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
// import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.senai.JOGGAR.dtos.EventoInputDTO;
import com.senai.JOGGAR.dtos.EventoOutputDTO;
import com.senai.JOGGAR.entities.Evento;
import com.senai.JOGGAR.repositories.EventoRepository;
import com.senai.JOGGAR.services.EventoService;
import com.senai.JOGGAR.entities.Atividade;
import com.senai.JOGGAR.entities.Endereco;
import com.senai.JOGGAR.entities.Tipo;

@ExtendWith(MockitoExtension.class)
public class EventoServiceTest {

    private static final Endereco Endereco = null;

    @Mock
    private EventoRepository repository;

    @InjectMocks
    private EventoService service;


    @Test
    public void testCreate() {
        EventoInputDTO inputDto = new EventoInputDTO();
        Evento eventoCriado = new Evento();
        when(repository.save(any(Evento.class))).thenReturn(eventoCriado);

        EventoOutputDTO outputDto = service.create(inputDto);

        assertEquals(eventoCriado.getId(), outputDto.getId());
        
        verify(repository, times(1)).save(any(Evento.class));
    }

    @Test
    public void testRead() {
        // Arrange
        Long eventId = 1L;
        Evento evento = new Evento();
        when(repository.findById(eventId)).thenReturn(Optional.of(evento));

        EventoOutputDTO outputDto = service.read(eventId);

        assertEquals(evento.getId(), outputDto.getId());
        
        verify(repository, times(1)).findById(eventId);
    }

    @Test
    public void testList() {
        List<Evento> eventos = List.of(new Evento(), new Evento());
        when(repository.findAll()).thenReturn(eventos);

        List<EventoOutputDTO> outputDtos = service.list();

        assertEquals(eventos.size(), outputDtos.size());
        
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testDelete() {
        Long eventId = 1L;

        service.delete(eventId);

        verify(repository, times(1)).deleteById(eventId);
    }

    @Test
    public void testUpdate() {
        EventoInputDTO inputDto = new EventoInputDTO();
        inputDto.setId(1L);
        Evento eventoAtualizado = new Evento();
        when(repository.existsById(inputDto.getId())).thenReturn(true);
        when(repository.save(any(Evento.class))).thenReturn(eventoAtualizado);

        EventoOutputDTO outputDto = service.update(inputDto);

        assertEquals(eventoAtualizado.getId(), outputDto.getId());
        
        verify(repository, times(1)).existsById(inputDto.getId());
        verify(repository, times(1)).save(any(Evento.class));
    }


    @Test
    public void eventoAtualizadoComSucesso() {

        EventoInputDTO eventoInput = new EventoInputDTO(1l, "Titulo", "Imagem", "Data", "Hora", Endereco, "Privacidade", "Descricao", Atividade.MMO, "Publico", Tipo.VIRTUAL);

        Evento evento = new Evento(eventoInput);
        evento.setId(1l);
         
        when(repository.existsById(anyLong())).thenReturn(true);
        when(repository.save(any())).thenReturn(evento);

        EventoOutputDTO eventoAtualizado = service.update(eventoInput);

        assertEquals(eventoInput.getId(), eventoAtualizado.getId());
        assertEquals(eventoInput.getTituloEvento(), eventoAtualizado.getTituloEvento());
        assertEquals(eventoInput.getImagemEvento(), eventoAtualizado.getImagemEvento());
        assertEquals(eventoInput.getDataEvento(), eventoAtualizado.getDataEvento());
        assertEquals(eventoInput.getHoraEvento(), eventoAtualizado.getHoraEvento());
        assertEquals(eventoInput.getPrivacidadeEvento(), eventoAtualizado.getPrivacidadeEvento());
        assertEquals(eventoInput.getDescricaoEvento(), eventoAtualizado.getDescricaoEvento());
        assertEquals(eventoInput.getPublicoEvento(), eventoAtualizado.getPublicoEvento());
        assertEquals(eventoInput.getEndereco(), eventoAtualizado.getEndereco());
        assertEquals(eventoInput.getAtividade(), eventoAtualizado.getAtividade());
        assertEquals(eventoInput.getTipo(), eventoAtualizado.getTipo());

    } 
}