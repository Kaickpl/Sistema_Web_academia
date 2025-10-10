package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Repositories.GrupoRepository;
import br.com.upe.academia.AcademiaWeb.Services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service


public class GrupoServiceImpl implements GrupoService {
    @Autowired
    private GrupoRepository grupoRepository;

    @Override
    public Grupo CriarGrupo(Grupo Grupo) {
        return grupoRepository.save(Grupo);
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
    public Grupo editarGrupo(Grupo Grupo) {
        return null;
    }

    @Override
    public Page<Grupo> buscarGrupos(Pageable page) {
        return grupoRepository.findAll(page);
    }

    @Override
    public List<Grupo> buscarGrupo(String nomeGrupo) {
        return grupoRepository.findByNomeGrupoContainingIgnoreCase(nomeGrupo);
    }
}
