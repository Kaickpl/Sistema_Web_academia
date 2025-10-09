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

public class ConquistasDTOs {

    private UUID idConquistas;
    private UUID alunoId;
    private String nomeConquista;
    private String descricaoConquista;

    public ConquistasDTOs(UUID alunoId, String descricaoConquista, UUID idConquistas, String nomeConquista) {
        this.alunoId = alunoId;
        this.descricaoConquista = descricaoConquista;
        this.idConquistas = idConquistas;
        this.nomeConquista = nomeConquista;
    }

}
