package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Services.SerieService;
import java.util.UUID;

public class ExecutavelAtualizarSerie implements Executavel {
    private SerieService serieService;
    private UUID id;
    private Serie serieAntiga;
    private Serie serieAtualizada;
    private Serie novaSerie;

    public ExecutavelAtualizarSerie(SerieService serieService, UUID id, Serie novaSerie) {
        this.serieService = serieService;
        this.id = id;
        this.novaSerie = novaSerie;
    }

    public Serie getSerieAtualizada() {
        return serieAtualizada;
    }

    @Override
    public void executar() {
        this.serieAntiga = serieService.buscarSerie(id);
        this.serieAtualizada = serieService.atualizarSerie(novaSerie);
    }

    @Override
    public void desfazer() {
        if (serieAtualizada != null) {
            serieService.atualizarSerie(serieAntiga);
        }
    }

}
