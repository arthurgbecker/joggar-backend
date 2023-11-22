package com.senai.JOGGAR;

import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
// import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.senai.JOGGAR.dtos.EventoInputDTO;
import com.senai.JOGGAR.dtos.EventoOutputDTO;
import com.senai.JOGGAR.entities.Atividade;
import com.senai.JOGGAR.entities.Endereco;
import com.senai.JOGGAR.entities.Evento;
import com.senai.JOGGAR.entities.Tipo;
import com.senai.JOGGAR.repositories.EventoRepository;
import com.senai.JOGGAR.services.EventoService;

@ExtendWith(MockitoExtension.class)
public class ConversoresTest {

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EventoService eventoService;

    @Test
    public void testConverterEntidadeParaDTO() {
        // Entidade Evento exemplo
        Evento evento = new Evento();
        evento.setId(1L);
        evento.setTituloEvento("Evento de Teste");
        evento.setImagemEvento("imagem.png");
        evento.setDataEvento("2022-01-01");
        evento.setHoraEvento("10:00");
        evento.setPrivacidadeEvento("Público");
        evento.setDescricaoEvento("Descrição do evento");
        evento.setPublicoEvento("Público-alvo");

        // Cria um exemplo de Endereco
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLocal("Rua Teste");
        evento.setEndereco(endereco);

        // Cria um exemplo de Atividade enum
        Atividade atividade = Atividade.VOLEI;
        evento.setAtividade(atividade);

        // Cria um exemplo de Tipo enum
        Tipo tipo = Tipo.PRESENCIAL;
        evento.setTipo(tipo);

        // Converte a entidade para DTO
        EventoOutputDTO dto = eventoService.converterEntidadeParaDTO(evento);

        // Asserts do DTO
        Assertions.assertEquals(evento.getId(), dto.getId());
        Assertions.assertEquals(evento.getTituloEvento(), dto.getTituloEvento());
        Assertions.assertEquals(evento.getImagemEvento(), dto.getImagemEvento());
        Assertions.assertEquals(evento.getDataEvento(), dto.getDataEvento());
        Assertions.assertEquals(evento.getHoraEvento(), dto.getHoraEvento());
        Assertions.assertEquals(evento.getPrivacidadeEvento(), dto.getPrivacidadeEvento());
        Assertions.assertEquals(evento.getDescricaoEvento(), dto.getDescricaoEvento());
        Assertions.assertEquals(evento.getPublicoEvento(), dto.getPublicoEvento());
        Assertions.assertEquals(endereco.getId(), dto.getEndereco().getId());
        Assertions.assertEquals(endereco.getLocal(), dto.getEndereco().getLocal());
        Assertions.assertEquals(atividade, dto.getAtividade());
        Assertions.assertEquals(tipo, dto.getTipo());
    }

    @Test
    public void testConverterDtoParaEntidade() {
        // Cria um exemplo de DTO
        EventoInputDTO dto = new EventoInputDTO();
        dto.setTituloEvento("Evento de Teste");
        dto.setImagemEvento("imagem.png");
        dto.setDataEvento("2022-01-01");
        dto.setHoraEvento("10:00");
        dto.setPrivacidadeEvento("Público");
        dto.setDescricaoEvento("Descrição do evento");
        dto.setPublicoEvento("Público-alvo");

        // Cria um exemplo de Endereco
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLocal("Rua Teste");
        dto.setEndereco(endereco);

        // Cria um exemplo de Atividade enum
        Atividade atividade = Atividade.MMO;
        dto.setAtividade(atividade);

        // Cria um exemplo de Tipo enum
        Tipo tipo = Tipo.VIRTUAL;
        dto.setTipo(tipo);

        // Converte o DTO para entidade
        Evento evento = eventoService.converterDtoParaEntidade(dto);

        // Asserts esperados da entidade
        Assertions.assertEquals(dto.getTituloEvento(), evento.getTituloEvento());
        Assertions.assertEquals(dto.getImagemEvento(), evento.getImagemEvento());
        Assertions.assertEquals(dto.getDataEvento().toString(), evento.getDataEvento());
        Assertions.assertEquals(dto.getHoraEvento().toString(), evento.getHoraEvento());
        Assertions.assertEquals(dto.getPrivacidadeEvento(), evento.getPrivacidadeEvento());
        Assertions.assertEquals(dto.getDescricaoEvento(), evento.getDescricaoEvento());
        Assertions.assertEquals(dto.getPublicoEvento(), evento.getPublicoEvento());
        Assertions.assertEquals(endereco.getId(), evento.getEndereco().getId());
        Assertions.assertEquals(endereco.getLocal(), evento.getEndereco().getLocal());
        Assertions.assertEquals(atividade, evento.getAtividade());
        Assertions.assertEquals(tipo, evento.getTipo());
    }
}