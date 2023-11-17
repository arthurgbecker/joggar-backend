package com.senai.JOGGAR.dtos;

import java.sql.Date;

import com.senai.JOGGAR.entities.Atividade;
import com.senai.JOGGAR.entities.Endereco;
import com.senai.JOGGAR.entities.Tipo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class EventoInputDTO{
    private Long id;
    private String tituloEvento;
    private String imagemEvento;
    private Date dataEvento;
    private String horaEvento;
    // private Endereco endereco;
    private String privacidadeEvento;
    private String descricaoEvento;
    private Atividade atividade;
    private String publicoEvento;
    private Tipo tipo;
}
