package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.ExercicioServiceImpl;

import java.util.UUID;

public class ExecutavelAtualizarExercicio implements Executavel{
    private ExercicioServiceImpl exercicioService;
    private UUID id;
    private Exercicio exercicioAntigo;
    private Exercicio exercicioAtualizado;
    private Exercicio exercicioNovo;

    public ExecutavelAtualizarExercicio(ExercicioServiceImpl service, UUID id, Exercicio exercicio) {
        this.exercicioService = service;
        this.id = id;
        this.exercicioAntigo = exercicio;

    }

    @Override
    public void executar() {
        this.exercicioAntigo = exercicioService.buscarExercicio(this.exercicioNovo.getIdExercicio());
        this.exercicioAtualizado = exercicioService.alterarExercicio(this.exercicioNovo);
    }

    @Override
    public void desfazer() {
        if (this.exercicioAntigo != null) {
            this.exercicioService.alterarExercicio(this.exercicioAntigo);
        }
    }
}
