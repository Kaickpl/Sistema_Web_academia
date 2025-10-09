package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Progressao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProgressaoService {
    public Progressao salvaCarga(UUID alunoId, String exercicio, int peso);

    List<Progressao> getHistoricoCarga(UUID alunoId, String nomeExercicico);
}
