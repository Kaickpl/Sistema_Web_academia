package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioSessaoService;
import br.com.upe.academia.AcademiaWeb.utils.ExercicioSessaoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExecutavelDeletarExercicioSessao implements Executavel{
    private ExercicioSessao sessaoDeletada;
    private List<SerieSessao> seriesSessoesSalvas;
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
        this.seriesSessoesSalvas = sessaoDeletada.getSeriesRealizadas().stream().map(this::clonarSerieSessao).collect(Collectors.toList());
        exercicioSessaoService.deletarExercicioSessao(this.idExercicioSessao);
    }

    @Override
    public void desfazer() {
        if(this.sessaoDeletada != null){
            this.sessaoDeletada.setIdExercicioSessao(null);
            this.sessaoDeletada.setSeriesRealizadas(new ArrayList<>());
            this.sessaoDeletada = exercicioSessaoService.salvarExercicioSessao(exercicioSessaoMapper.toDTO(this.sessaoDeletada));
            if(!this.seriesSessoesSalvas.isEmpty()){
                exercicioSessaoService.reincerirSeries(seriesSessoesSalvas, this.sessaoDeletada.getIdExercicioSessao());
            }
        }
    }

    public SerieSessao clonarSerieSessao(SerieSessao original){
        SerieSessao copia = new SerieSessao();
        copia.setPeso(original.getPeso());
        copia.setNumeroDeRepeticoes(original.getNumeroDeRepeticoes());
        return copia;
    }
}

