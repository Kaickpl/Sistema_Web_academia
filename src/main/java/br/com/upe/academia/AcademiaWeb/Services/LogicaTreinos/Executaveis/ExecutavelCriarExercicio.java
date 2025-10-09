package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.ExercicioServiceImpl;
import lombok.Getter;

public class ExecutavelCriarExercicio implements Executavel{

    private ExercicioServiceImpl exercicioServiceImpl;
    private Exercicio exercicio;
    @Getter
    private Exercicio exercicioCriado;

    public ExecutavelCriarExercicio(ExercicioServiceImpl exercicioServiceImpl, Exercicio exercicio) {
        this.exercicioServiceImpl = exercicioServiceImpl;
        this.exercicio = exercicio;
    }

    @Override
    public void executar() {
        this.exercicioCriado = exercicioServiceImpl.adicionarExercicio(exercicio);
    }

    @Override
    public void desfazer() {
        if(exercicioCriado != null){
            this.exercicioServiceImpl.removerExercicio(exercicioCriado.getIdExercicio());
        }

    }
}
