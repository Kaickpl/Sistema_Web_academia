package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ComentarioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioSessaoService;

import java.util.UUID;

public class ExecutavelAddComentarioExercicioSessao implements Executavel {
    private String comentarioOriginal;
    private String comentarioNovo;
    private UUID idSessao;
    private UUID exercicioExecucaoId;
    private ExercicioSessaoService service;

    public ExecutavelAddComentarioExercicioSessao(ExercicioSessaoService service,UUID idSessao ,UUID exercicioSessaoId, ComentarioDTO comentario) {
        this.exercicioExecucaoId = exercicioSessaoId;
        this.comentarioNovo = comentario.getComentario();
        this.service = service;
        this.idSessao = idSessao;
    }

    @Override
    public void executar() {
        ExercicioSessao sessao = service.buscarExercicioSessao(idSessao, exercicioExecucaoId);
        this.comentarioOriginal = sessao.getComentario();
        service.adicionarComentario(idSessao, exercicioExecucaoId ,comentarioNovo);
    }

    @Override
    public void desfazer() {
        if(this.comentarioOriginal != null){
            this.service.adicionarComentario(idSessao ,exercicioExecucaoId,comentarioOriginal);
        } else {
          this.service.adicionarComentario( idSessao,exercicioExecucaoId, null);
        }
    }
}
