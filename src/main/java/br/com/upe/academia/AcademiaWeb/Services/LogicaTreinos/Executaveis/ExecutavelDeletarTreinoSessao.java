package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoSessao;
import br.com.upe.academia.AcademiaWeb.Services.TreinoSessaoService;
import br.com.upe.academia.AcademiaWeb.utils.TreinoSessaoMapper;
import java.util.UUID;

public class ExecutavelDeletarTreinoSessao implements Executavel {
    private TreinoSessaoService treinoSessaoService;
    private TreinoSessao treinoSessaoDeletada;
    private UUID idTreinoSessao;
    private TreinoSessaoMapper treinoSessaoMapper;

    public ExecutavelDeletarTreinoSessao(TreinoSessaoService treinoSessaoService, UUID idTreinoSessao) {
        this.treinoSessaoService = treinoSessaoService;
        this.idTreinoSessao = idTreinoSessao;
    }

    @Override
    public void executar() {
        treinoSessaoDeletada = treinoSessaoService.buscarSessaoPorId(this.idTreinoSessao);
        treinoSessaoService.apagarTreinoSessao(idTreinoSessao);
    }

    @Override
    public void desfazer() {
        if(this.treinoSessaoDeletada != null){
            this.treinoSessaoDeletada.setIdTreinoSessao(null);
            this.treinoSessaoDeletada = treinoSessaoService.recriarTreinoSessao(this.treinoSessaoDeletada);
        }
    }
}