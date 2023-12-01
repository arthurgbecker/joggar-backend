package com.senai.JOGGAR.entities;

import java.util.List;

import com.senai.JOGGAR.dtos.EventoInputDTO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    public Evento(EventoInputDTO dto) {
        this.tituloEvento = dto.getTituloEvento();
        this.imagemEvento = dto.getImagemEvento();
        this.dataEvento = dto.getDataEvento();
        this.horaEvento = dto.getHoraEvento();
        this.privacidadeEvento = dto.getPrivacidadeEvento();
        this.descricaoEvento = dto.getDescricaoEvento();
        this.publicoEvento = dto.getPublicoEvento();
        this.endereco = dto.getEndereco();
        this.atividade = dto.getAtividade();
        this.tipo = dto.getTipo();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String tituloEvento;
    private String imagemEvento;
    private String dataEvento;
    private String horaEvento;
    private String privacidadeEvento;
    private String descricaoEvento;
    private String publicoEvento;

    @ManyToOne(cascade = { CascadeType.ALL })
    private Endereco endereco;

    @ManyToMany(mappedBy = "eventos")
    private List<Usuario> usuarios;

    @Enumerated(EnumType.STRING)
    private Atividade atividade;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

}
