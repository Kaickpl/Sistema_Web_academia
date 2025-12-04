package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ComentarioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoSessao;
import br.com.upe.academia.AcademiaWeb.Services.TreinoSessaoService;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ExecutavelAddComentarioTreinoSessao implements Executavel{
    private TreinoSessaoService treinoSessaoService;
    private UUID idSessao;
    private UUID idAluno;
    private String comentarioNovo;
    private String comentarioOriginal;
    private TreinoSessao treinoSessao;

    public ExecutavelAddComentarioTreinoSessao(TreinoSessaoService treinoSessaoService, UUID idSessao, UUID idAluno ,ComentarioDTO comentario) {
        this.treinoSessaoService = treinoSessaoService;
        this.idSessao = idSessao;
        this.comentarioNovo = comentario.getComentario();
        this.idAluno = idAluno;
    }

    @Override
    public void executar() {
        TreinoSessao sessao = treinoSessaoService.buscarSessaoPorId( idAluno,idSessao);
        this.comentarioOriginal = sessao.getComentario();
        this.treinoSessao = treinoSessaoService.adicionarComentario(idSessao, idAluno ,comentarioNovo);
    }

    @Override
    public void desfazer() {
        this.treinoSessaoService.adicionarComentario(idSessao, idAluno, this.comentarioOriginal);
    }
}
