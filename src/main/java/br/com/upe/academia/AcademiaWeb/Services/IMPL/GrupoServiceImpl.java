package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.GrupoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.GrupoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.PersonalRepository;
import br.com.upe.academia.AcademiaWeb.Services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service


public class GrupoServiceImpl implements GrupoService {
    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PersonalRepository personalRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Grupo CriarGrupo(Grupo grupo) {
        if (grupo.getPersonal() == null || grupo.getPersonal().getIdUsuario() == null) {
            return null;
        }
        if (grupo.getNomeGrupo() == null) {
            return null;
        }
        Optional<Personal> personal = personalRepository.findById(grupo.getPersonal().getIdUsuario());
        if (personal.isEmpty()) {
            return null;
        }

        if (grupo.getPersonal() == null || grupo.getPersonal().getIdUsuario() == null) {
            return null;
        }
        if (grupo.getAlunos() == null) {
            grupo.setAlunos(new ArrayList<>());
        }

        grupo.setPersonal(personal.get());


        return grupoRepository.save(grupo);
    }

    @Override
    public Boolean removerGrupo(UUID id) {
        if (grupoRepository.existsById(id)) {
            grupoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Grupo editarGrupo(UUID id,GrupoDTOs grupoDTOs) {
        Optional<Grupo> grupoEncontrado = grupoRepository.findById(id);
        if(grupoEncontrado.isEmpty()) {
            return null;
        }
        Grupo grupoExiste = grupoEncontrado.get();
        if (grupoDTOs.getNomeGrupo() != null) {
            grupoExiste.setNomeGrupo(grupoDTOs.getNomeGrupo());
        }
        if(grupoDTOs.getDescricaoGrupo() != null) {
            grupoExiste.setDescricaoGrupo(grupoDTOs.getDescricaoGrupo());
        }
        return grupoRepository.save(grupoExiste);
    }

    @Override
    public Page<Grupo> buscarGrupos(Pageable page) {
        return grupoRepository.findAll(page);
    }

    @Override
    public List<Grupo> buscarGrupo(String nomeGrupo) {
        return grupoRepository.findByNomeGrupoContainingIgnoreCase(nomeGrupo);
    }

    @Override
    public Grupo AddUsuarioGrupo(UUID idUsuario, GrupoDTOs grupoDTOs) {
        Optional<Grupo> grupo = grupoRepository.findById(grupoDTOs.getIdGrupo());
        if (grupo.isEmpty()) {
            return null;
        }
        Grupo grupo1 = grupo.get();
        if (grupo1.getAlunos() == null) {
            grupo1.setAlunos(new ArrayList<>());
        }
        Optional<Aluno> aluno = alunoRepository.findById(idUsuario);
        if (aluno.isEmpty()) {
            return null;
        }
        if (grupo1.getAlunos().contains(aluno.get())) {
            return grupo1;
        }
        grupo1.getAlunos().add(aluno.get());
        return  grupoRepository.save(grupo1);
    }

    @Override
    public Grupo removeUsuarioGrupo(UUID idUsuario, GrupoDTOs grupoDTOs) {
        Optional<Grupo> grupo = grupoRepository.findById(grupoDTOs.getIdGrupo());
        if (grupo.isEmpty()) {
            return null;
        }
        Optional<Aluno> aluno = alunoRepository.findById(idUsuario);
        if (aluno.isEmpty()) {
            return null;
        }
        Grupo grupo1 = grupo.get();
        if (grupo1.getAlunos() == null) {
            return null;
        }
        grupo1.getAlunos().remove(aluno.get());
        return grupoRepository.save(grupo1);
    }

}
