package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Services.SerieService;

import java.util.UUID;

public class ExecutavelDeletarSerie implements Executavel{
    private SerieService serieService;
    private UUID id;
    private Serie serieRemovida;

    public ExecutavelDeletarSerie(SerieService serieService, UUID id) {
        this.serieService = serieService;
        this.id = id;
    }

    @Override
    public void executar() {
        this.serieRemovida = serieService.buscarSerie(id);
        serieService.removerSerie(id);
    }

    @Override
    public void desfazer(){
        if(this.serieRemovida != null){
            serieRemovida = serieService.adicionarSerie(serieRemovida);
        }
    }

    public Serie getSerieRemovida() {
        return serieRemovida;
    }

}
