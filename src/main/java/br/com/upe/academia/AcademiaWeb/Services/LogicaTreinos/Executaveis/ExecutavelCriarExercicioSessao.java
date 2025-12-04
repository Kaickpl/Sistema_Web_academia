package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioSessaoService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ExecutavelCriarExercicioSessao implements Executavel{
    private ExercicioSessao exercicioSessao;
    private ExercicioSessaoDTO exercicioSessaoDTO;
    private ExercicioSessaoService exercicioSessaoService;
    private UUID idTreinoSessao;
    private UUID idExercicioSessao;
    private List<SerieSessao> seriesSalvas;

    public ExecutavelCriarExercicioSessao(ExercicioSessaoService exercicioSessaoService, ExercicioSessaoDTO exercicioSessaoDTO) {
        this.exercicioSessaoService = exercicioSessaoService;
        this.exercicioSessaoDTO = exercicioSessaoDTO;
        this.idTreinoSessao = exercicioSessaoDTO.getIdTreinoSessao();
    }

    @Override
    public void executar() {
        this.exercicioSessaoDTO.setIdExercicioSessao(null);
        this.exercicioSessao = exercicioSessaoService.salvarExercicioSessao(exercicioSessaoDTO);
        this.idExercicioSessao = exercicioSessao.getIdExercicioSessao();
    }

    @Override
    public void desfazer() {
        if(this.idExercicioSessao != null){
            this.seriesSalvas = this.exercicioSessao.getSeriesRealizadas();
            exercicioSessaoService.deletarExercicioSessao(this.idExercicioSessao, idTreinoSessao);
        }
    }
}
