package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.GrupoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Grupo;
import br.com.upe.academia.AcademiaWeb.Entities.Personal;
import br.com.upe.academia.AcademiaWeb.Exceptions.CampoObrigatorioException;
import br.com.upe.academia.AcademiaWeb.Exceptions.InformacaoNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.OperacaoNaoPermitidaException;
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
            throw new CampoObrigatorioException("O personal deve ser informado");
        }
        if (grupo.getNomeGrupo() == null || grupo.getNomeGrupo().isBlank()) {
            throw new CampoObrigatorioException("O nome do grupo deve ser informado");
        }
        if (grupo.getDescricaoGrupo() == null || grupo.getDescricaoGrupo().isBlank()) {
            throw new CampoObrigatorioException("A descrição do grupo deve ser informada");
        }
        Optional<Personal> personal = personalRepository.findById(grupo.getPersonal().getIdUsuario());
        if (personal.isEmpty()) {
            throw new InformacaoNaoEncontradoException("O personal informado, não foi encontrado.");
        }
        if (grupo.getAlunos() == null) {
            grupo.setAlunos(new ArrayList<>());
        }
        grupo.setPersonal(personal.get());
        return grupoRepository.save(grupo);
    }

    @Override
    public void removerGrupo(UUID id) {
        if (!grupoRepository.existsById(id)) {
            throw new InformacaoNaoEncontradoException("Grupo com esse ID: " + id+ " não encontrado, tente novamento com outro ID");
        }
        grupoRepository.deleteById(id);
    }

    @Override
    public Grupo editarGrupo(UUID id,GrupoDTOs grupoDTOs) {
        Optional<Grupo> grupoEncontrado = grupoRepository.findById(id);
        if(grupoEncontrado.isEmpty()) {
            throw new InformacaoNaoEncontradoException("Grupo não encontrado, tente novamente.");
        }
        Grupo grupoExiste = grupoEncontrado.get();
        if (grupoDTOs.getNomeGrupo() != null ) {

            if (grupoDTOs.getNomeGrupo().equals(grupoExiste.getNomeGrupo())) {
                throw new OperacaoNaoPermitidaException("O nome do grupo é igual ao atual. Nenhuma alteração foi realizada.");
            }
            if (grupoDTOs.getNomeGrupo().isBlank()){
                throw new CampoObrigatorioException("Nome do grupo deve ser informado");
            }
            grupoExiste.setNomeGrupo(grupoDTOs.getNomeGrupo());
        }
        if(grupoDTOs.getDescricaoGrupo() != null) {
            if (grupoDTOs.getDescricaoGrupo().equals(grupoExiste.getDescricaoGrupo())) {
                throw new OperacaoNaoPermitidaException("A descrição do grupo é igual ao atual. Nenhuma alteração foi realizada.");
            }
            if (grupoDTOs.getDescricaoGrupo().isBlank()){
                throw new CampoObrigatorioException("Descrição do grupo deve ser informado");
            }
            grupoExiste.setDescricaoGrupo(grupoDTOs.getDescricaoGrupo());
        }
        return grupoRepository.save(grupoExiste);
    }

    @Override
    public Page<Grupo> buscarGrupos(Pageable page) {
        Page<Grupo> gruposEncontradas = grupoRepository.findAll(page);
        if (gruposEncontradas.isEmpty()) {
            throw new InformacaoNaoEncontradoException("Nenhum grupo cadastrado foi encontrado.");

        }
        return grupoRepository.findAll(page);
    }

    @Override
    public List<Grupo> buscarGrupo(String nomeGrupo) {
        if (nomeGrupo == null || nomeGrupo.isBlank()) {
            throw new CampoObrigatorioException("O campo nome, para busca é obtigatório.");
        }
        List<Grupo> grupos = grupoRepository.findByNomeGrupoContainingIgnoreCase(nomeGrupo);
        if (grupos.isEmpty()) {
            throw new InformacaoNaoEncontradoException("Nenhum grupo com esse nome: " + nomeGrupo);
        }
        return grupos;
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
