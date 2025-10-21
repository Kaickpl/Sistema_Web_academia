package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;

import java.util.UUID;

public class ExecutavelDeletarExercicio implements Executavel{
    private ExercicioService exercicioService;
    private UUID idExercicio;
    private Exercicio exercicioRemovido;

    public ExecutavelDeletarExercicio(ExercicioService exercicioService, UUID idExercicio) {
        this.exercicioService = exercicioService;
        this.idExercicio = idExercicio;
    }

    @Override
    public void executar() {
        exercicioRemovido = this.exercicioService.buscarExercicio(idExercicio);
        exercicioService.removerExercicio(idExercicio);
    }

    @Override
    public void desfazer() {
        if (exercicioRemovido != null) {
            exercicioService.adicionarExercicio(exercicioRemovido);
        }
    }
}
