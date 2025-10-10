package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;


import java.util.UUID;


public class ExecutavelAtualizarExercicio implements Executavel {
    private final ExercicioService exercicioService;
    private final UUID id;
    private final Exercicio dadosNovos;

    private Exercicio exercicioAntigo;
    private Exercicio exercicioAtualizado;

    public ExecutavelAtualizarExercicio(ExercicioService service, UUID id, Exercicio dadosNovos) {
        this.exercicioService = service;
        this.id = id;
        this.dadosNovos = dadosNovos;
    }

    @Override
    public void executar() {
        this.exercicioAntigo = exercicioService.buscarExercicio(this.id);
        exercicioAtualizado = exercicioService.alterarExercicio(this.dadosNovos);
    }


    @Override
    public void desfazer() {
        if (this.exercicioAntigo != null) {
            this.exercicioAtualizado = exercicioService.alterarExercicio(this.exercicioAntigo);
        }
    }

    public Exercicio getExercicioAtualizado() {
        return exercicioAtualizado;
    }
}


