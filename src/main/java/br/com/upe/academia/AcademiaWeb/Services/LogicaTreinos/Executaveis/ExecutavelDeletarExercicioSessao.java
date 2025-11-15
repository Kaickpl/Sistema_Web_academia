package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioSessaoService;
import br.com.upe.academia.AcademiaWeb.utils.ExercicioSessaoMapper;

import java.util.ArrayList;
import java.util.UUID;

public class ExecutavelDeletarExercicioSessao implements Executavel{
    private ExercicioSessao sessaoDeletada;
    private ExercicioSessaoService exercicioSessaoService;
    private UUID idExercicioSessao;
    private ExercicioSessaoMapper exercicioSessaoMapper;

    public ExecutavelDeletarExercicioSessao(ExercicioSessaoService exercicioSessaoService, ExercicioSessaoMapper exercicioSessaoMapper ,ExercicioSessaoDTO exercicioSessaoDTO) {
        this.exercicioSessaoService = exercicioSessaoService;
        this.exercicioSessaoMapper = exercicioSessaoMapper;
        this.idExercicioSessao = exercicioSessaoDTO.getIdExercicioSessao();
    }

    @Override
    public void executar() {
        this.sessaoDeletada = exercicioSessaoService.buscarExercicioSessao(this.idExercicioSessao);
        exercicioSessaoService.deletarExercicioSessao(this.idExercicioSessao);
    }

    @Override
    public void desfazer() {
        if(sessaoDeletada != null){
            this.sessaoDeletada.setSeriesRealizadas(new ArrayList<>());
            this.sessaoDeletada.setIdExercicioSessao(null);
        }
        this.sessaoDeletada = exercicioSessaoService.salvarExercicioSessao(exercicioSessaoMapper.toDTO(this.sessaoDeletada));
    }
}

