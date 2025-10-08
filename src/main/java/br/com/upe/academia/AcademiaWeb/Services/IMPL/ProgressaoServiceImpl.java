package br.com.upe.academia.AcademiaWeb.Services.IMPL;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Progressao;
import br.com.upe.academia.AcademiaWeb.Repositories.ProgressaoRepository;
import br.com.upe.academia.AcademiaWeb.Services.ProgressaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Service
public class ProgressaoServiceImpl implements ProgressaoService{

    @Autowired
    ProgressaoRepository progressaoRepository;

    @Override
    public Progressao salvaCarga(UUID alunoId, String nomeExercicio, int peso) {
        Progressao progressao = new Progressao();
        progressao.setAlunoId(alunoId);
        progressao.setNomeExercicio(nomeExercicio);
        progressao.setPeso(peso);
        progressao.setData(LocalDate.now());

        return progressaoRepository.save(progressao);
    }

    @Override
    public List<Progressao> getHistoricoCarga(UUID alunoId, String nomeExercicico) {
        return progressaoRepository.findByAlunoIdAndNomeExercicioOrderByDataAsc(alunoId, nomeExercicico);
    }
}
