package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.Aluno;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Progressao;
import br.com.upe.academia.AcademiaWeb.Repositories.ProgressaoRepository;
import br.com.upe.academia.AcademiaWeb.Services.ProgressaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class ProgressaoServiceImpl implements ProgressaoService{

    @Autowired
    ProgressaoRepository progressaoRepository;

    @Override
    public Progressao salvaCarga(UUID AlunoId, String nomeExercicio, int peso) {
        Progressao progressao = new Progressao();
        progressao.setAlunoId(AlunoId);
        progressao.setNomeExercicio(nomeExercicio);
        progressao.setPeso(peso);

        return progressaoRepository.save(progressao);
    }
}
