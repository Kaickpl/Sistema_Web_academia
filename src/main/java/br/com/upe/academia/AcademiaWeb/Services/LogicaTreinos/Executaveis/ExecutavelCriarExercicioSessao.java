package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioSessaoService;

import java.util.List;
import java.util.UUID;

// mexer aqui pra implementar o redo colocando junto as series filhas

public class ExecutavelCriarExercicioSessao implements Executavel{
    private ExercicioSessao exercicioSessao;
    private ExercicioSessaoDTO exercicioSessaoDTO;
    private ExercicioSessaoService exercicioSessaoService;
    private UUID idExercicioSessao;
    private List<SerieSessao> seriesSalvas;

    public ExecutavelCriarExercicioSessao(ExercicioSessaoService exercicioSessaoService, ExercicioSessaoDTO exercicioSessaoDTO) {
        this.exercicioSessaoService = exercicioSessaoService;
        this.exercicioSessaoDTO = exercicioSessaoDTO;
    }

    @Override
    public void executar() {
        this.exercicioSessao = exercicioSessaoService.salvarExercicioSessao(exercicioSessaoDTO);
        this.idExercicioSessao = exercicioSessao.getIdExercicioSessao();
        this.seriesSalvas = this.exercicioSessao.getSeriesRealizadas();
    }

    @Override
    public void desfazer() {
        if(this.idExercicioSessao != null){
            exercicioSessaoService.deletarExercicioSessao(idExercicioSessao);
        }
    }
}
