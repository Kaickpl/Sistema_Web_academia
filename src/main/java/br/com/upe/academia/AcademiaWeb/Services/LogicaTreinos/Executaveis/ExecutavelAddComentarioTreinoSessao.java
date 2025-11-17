package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ComentarioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoSessao;
import br.com.upe.academia.AcademiaWeb.Services.TreinoSessaoService;

import java.util.UUID;

public class ExecutavelAddComentarioTreinoSessao implements Executavel{
    private final TreinoSessaoService treinoSessaoService;
    private final UUID idSessao;
    private final String comentarioNovo;

    // Campos para Rollback
    private String comentarioOriginal;

    public ExecutavelAddComentarioTreinoSessao(TreinoSessaoService treinoSessaoService, UUID idSessao, ComentarioDTO comentario) {
        this.treinoSessaoService = treinoSessaoService;
        this.idSessao = idSessao;
        this.comentarioNovo = comentario.getComentario();
    }

    @Override
    public void executar() {
        TreinoSessao sessao = treinoSessaoService.buscarSessaoPorId(idSessao);
        this.comentarioOriginal = sessao.getComentario();
        this.treinoSessaoService.adicionarComentario(idSessao, comentarioNovo);
    }

    @Override
    public void desfazer() {
        this.treinoSessaoService.adicionarComentario(idSessao, this.comentarioOriginal);
    }
}
