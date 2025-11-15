package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Conquistas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConquistaRegistroDTO {
    private UUID alunoId;
    private String nomeConquista;
    private String descricaoConquista;
    private int moedas;

    public ConquistaRegistroDTO(Conquistas conquistas) {
        this.alunoId = conquistas.getAluno().getIdUsuario();
        this.descricaoConquista = conquistas.getDescricaoConquista();
        this.nomeConquista = conquistas.getNomeConquista();
        this.moedas = conquistas.getMoedas();
    }
}
