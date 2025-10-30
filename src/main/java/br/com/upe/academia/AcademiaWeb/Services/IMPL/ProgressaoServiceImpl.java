package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ProgressaoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Progressao;
import br.com.upe.academia.AcademiaWeb.Repositories.AlunoRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.ProgressaoRepository;
import br.com.upe.academia.AcademiaWeb.Services.ProgressaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class

ProgressaoServiceImpl implements ProgressaoService{

    @Autowired
    ProgressaoRepository progressaoRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Override
    public Progressao salvaCarga(ProgressaoDTOs progressaoDTOs) {
        Progressao novaProgressao = new Progressao();
        Aluno aluno = alunoRepository.findByIdUsuario(progressaoDTOs.getAlunoId());

        novaProgressao.setAluno(aluno);
        novaProgressao.setNomeExercicio(progressaoDTOs.getNomeExercicio());
        novaProgressao.setPeso(progressaoDTOs.getPeso());
        return progressaoRepository.save(novaProgressao);
    }

    @Override
    public List<Progressao> getHistoricoCarga(UUID alunoId, String nomeExercicico) {
        return progressaoRepository.findByAlunoIdUsuarioAndNomeExercicioOrderByDataAsc(alunoId, nomeExercicico);
    }
}
