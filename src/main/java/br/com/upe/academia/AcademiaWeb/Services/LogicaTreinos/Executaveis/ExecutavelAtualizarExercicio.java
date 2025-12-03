package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
public class ExecutavelAtualizarExercicio implements Executavel {
    private final ExercicioService exercicioService;
    private final UUID idExercicio;
    private final Exercicio dadosNovos;

    private Exercicio exercicioAntigo;
    private Exercicio exercicioAtualizado;

    public ExecutavelAtualizarExercicio(ExercicioService service, UUID id, Exercicio dadosNovos) {
        this.exercicioService = service;
        this.idExercicio = id;
        dadosNovos.setIdExercicio(id);
        this.dadosNovos = dadosNovos;
    }

    @Override
    public void executar() {
        Exercicio estadoAtualNoBanco = exercicioService.buscarExercicio(this.idExercicio);
        this.exercicioAntigo = new Exercicio();
        this.exercicioAntigo.setIdExercicio(estadoAtualNoBanco.getIdExercicio());
        this.exercicioAntigo.setNomeExercicio(estadoAtualNoBanco.getNomeExercicio());
        this.exercicioAntigo.setDescricaoExercicio(estadoAtualNoBanco.getDescricaoExercicio());
        this.exercicioAntigo.setMusculoPrincipal(estadoAtualNoBanco.getMusculoPrincipal());

        this.exercicioAtualizado = exercicioService.alterarExercicio(this.dadosNovos);
    }


    @Override
    public void desfazer() {
        if (this.exercicioAntigo != null) {
            this.exercicioAtualizado = exercicioService.alterarExercicio(this.exercicioAntigo);
        }
    }
}


