package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.GrupoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.UsuarioDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface GrupoService {

    public Grupo CriarGrupo(Grupo Grupo);

    public void removerGrupo(UUID id);

    public Grupo editarGrupo(UUID id, GrupoDTOs grupoDTOs);

    public Page<Grupo> buscarGrupos(Pageable page);

    public List<Grupo> buscarGrupo(String nomeGrupo);

    public Grupo AddUsuarioGrupo (UUID idUsuario, GrupoDTOs grupoDTOs);

    public Grupo removeUsuarioGrupo (UUID idUsuario, GrupoDTOs grupoDTOs);

    public Grupo BuscarAlunoGrupo(String nomeGrupo);

}
