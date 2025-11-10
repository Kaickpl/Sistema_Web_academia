package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoEmGrupoDTOsResponse {

    private String nomeGrupo;
    private List<AlunoResponseGrupoDTOs> alunoResponseGrupos;

    public AlunoEmGrupoDTOsResponse(Grupo grupo) {
        this.nomeGrupo = grupo.getNomeGrupo();
        this.alunoResponseGrupos = new ArrayList<>();
    }

}
