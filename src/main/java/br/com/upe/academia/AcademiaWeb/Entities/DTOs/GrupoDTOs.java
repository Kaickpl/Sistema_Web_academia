package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Grupo;

import java.util.UUID;

public class GrupoDTOs {
    private UUID idGrupo;
    private String nomeGrupo;
    private String DescricaoGrupo;

    public GrupoDTOs(Grupo grupo) {
        this.idGrupo = grupo.getIdGrupo();
        this.nomeGrupo = grupo.getNomeGrupo();
        this.DescricaoGrupo = grupo.getDescricaoGrupo();
    }
}
