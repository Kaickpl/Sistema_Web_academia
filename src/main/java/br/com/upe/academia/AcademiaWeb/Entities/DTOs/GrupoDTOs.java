package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GrupoDTOs {
    private UUID idGrupo;
    private String nomeGrupo;
    private String DescricaoGrupo;
    private List<Aluno> aluno;
    private Personal personal;

    public GrupoDTOs(Grupo grupo) {
        this.idGrupo = grupo.getIdGrupo();
        this.nomeGrupo = grupo.getNomeGrupo();
        this.DescricaoGrupo = grupo.getDescricaoGrupo();
        this.aluno =  new ArrayList<>();
        this.personal = grupo.getPersonal();
    }

}
