package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Services.SerieService;

import java.util.UUID;

public class ExecutavelDeletarSerie implements Executavel{
    private SerieService serieService;
    private UUID idOriginal;
    private Serie serieRemovida;

    public ExecutavelDeletarSerie(SerieService serieService, UUID idOriginal) {
        this.serieService = serieService;
        this.idOriginal = idOriginal;
    }

    @Override
    public void executar() {
        UUID idParaBuscar;
        if(this.serieRemovida == null){
            idParaBuscar = this.idOriginal;
        } else {
            idParaBuscar = this.serieRemovida.getIdSerie();
        }
        this.serieRemovida = serieService.buscarSerie(idParaBuscar);
        serieService.removerSerie(idParaBuscar);
    }

    @Override
    public void desfazer(){
        if(this.serieRemovida != null){
            this.serieRemovida.setIdSerie(null);
            this.serieRemovida = serieService.adicionarSerie(serieRemovida);
        }
    }

}
