package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ExercicioSessaoService {
    public ExercicioSessao buscarExercicioSessao(UUID id);

    public List<SerieSessao> listarSeriesExecucao(UUID idExercicio);

    public ExercicioSessao salvarExercicioSessao(ExercicioSessao exerciciosessao);

    public void deletarExercicioSessao(UUID idExercicio);


}
