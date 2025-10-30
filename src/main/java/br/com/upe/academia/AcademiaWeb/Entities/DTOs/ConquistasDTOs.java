package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
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

public class ConquistasDTOs {

    private UUID idConquistas;
    private UUID alunoId;
    private String nomeConquista;
    private String descricaoConquista;
    private int moedas;

    //tentar tirar o idconquistas

    public ConquistasDTOs(UUID alunoId, String descricaoConquista, UUID idConquistas, String nomeConquista, int moedas) {
        this.alunoId = alunoId;
        this.descricaoConquista = descricaoConquista;
        this.idConquistas = idConquistas;
        this.nomeConquista = nomeConquista;
        this.moedas = moedas;
    }

}
