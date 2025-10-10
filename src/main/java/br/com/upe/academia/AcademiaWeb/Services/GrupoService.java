package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface GrupoService {

    public Grupo CriarGrupo(Grupo Grupo);

    public Boolean removerGrupo(UUID id);

    public Grupo editarGrupo(Grupo Grupo);

    public Page<Grupo> buscarGrupos(Pageable page);

    public List<Grupo> buscarGrupo(String nomeGrupo);


}
