package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<AlunoResponseGrupoDTOs> alunoResponseGrupos;
    private PersonalResponseGrupoDTOs personalResponseGrupo;

    public GrupoDTOs(Grupo grupo) {
        this.idGrupo = grupo.getIdGrupo();
        this.nomeGrupo = grupo.getNomeGrupo();
        this.DescricaoGrupo = grupo.getDescricaoGrupo();
        this.alunoResponseGrupos =  grupo.getAlunos().stream().map(AlunoResponseGrupoDTOs::new).collect(Collectors.toList());
        this.personalResponseGrupo =  new PersonalResponseGrupoDTOs(grupo.getPersonal());


    }

}
