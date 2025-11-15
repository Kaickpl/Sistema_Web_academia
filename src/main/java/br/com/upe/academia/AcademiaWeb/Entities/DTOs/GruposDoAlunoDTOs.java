package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GruposDoAlunoDTOs {
    private UUID idGrupo;
    private String nomeGrupo;
    private String DescricaoGrupo;
    private PersonalResponseGrupoDTOs personalResponseGrupo;

    public GruposDoAlunoDTOs(Grupo grupo) {
        this.idGrupo = grupo.getIdGrupo();
        this.nomeGrupo = grupo.getNomeGrupo();
        this.DescricaoGrupo = grupo.getDescricaoGrupo();
        this.personalResponseGrupo = new PersonalResponseGrupoDTOs(grupo.getPersonal());

    }
}
