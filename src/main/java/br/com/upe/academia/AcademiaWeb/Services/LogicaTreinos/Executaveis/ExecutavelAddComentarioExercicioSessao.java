package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ComentarioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioSessaoService;

import java.util.UUID;

public class ExecutavelAddComentarioExercicioSessao implements Executavel {
    private String comentarioOriginal;
    private String comentarioNovo;
    private UUID exercicioExecucaoId;
    private ExercicioSessaoService service;

    public ExecutavelAddComentarioExercicioSessao(ExercicioSessaoService service,UUID exercicioSessaoId, ComentarioDTO comentario) {
        this.exercicioExecucaoId = exercicioSessaoId;
        this.comentarioNovo = comentario.getComentario();
        this.service = service;
    }

    @Override
    public void executar() {
        ExercicioSessao sessao = service.buscarExercicioSessao(exercicioExecucaoId);
        this.comentarioOriginal = sessao.getComentario();
        service.adicionarComentario(exercicioExecucaoId, comentarioNovo);
    }

    @Override
    public void desfazer() {
        if(this.comentarioOriginal != null){
            this.service.adicionarComentario(exercicioExecucaoId, comentarioOriginal);
        } else {
          this.service.adicionarComentario(exercicioExecucaoId, null);
        }
    }
}
