package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.ExercicioServiceImpl;
import lombok.Getter;

public class ExecutavelCriarExercicio implements Executavel{

    private ExercicioService exercicioService;
    private Exercicio exercicio;
    @Getter
    private Exercicio exercicioCriado;

    public ExecutavelCriarExercicio(ExercicioService exercicioServiceImpl, Exercicio exercicio) {
        this.exercicioService = exercicioServiceImpl;
        this.exercicio = exercicio;
    }

    @Override
    public void executar() {
        if(this.exercicio.getIdExercicio() != null) {
            this.exercicio.setIdExercicio(null);
        }
        this.exercicioCriado = exercicioService.adicionarExercicio(exercicio);
    }

    @Override
    public void desfazer() {
        if(exercicioCriado != null){
            this.exercicioService.removerExercicio(exercicioCriado.getIdExercicio());
        }

    }
}
