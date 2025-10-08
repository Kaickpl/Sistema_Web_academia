package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Services.SerieService;
import org.springframework.stereotype.Component;

public class ExecutavelCriarSerie implements Executavel {
    private SerieService serieService;
    private Serie serie;
    private Serie serieCriada;

    public ExecutavelCriarSerie(SerieService serieService, Serie serie) {
        this.serieService = serieService;
        this.serie = serie;
    }

    public Serie getSerieCriada() {
        return serieCriada;
    }

    @Override
    public void executar() {
        this.serieCriada = serieService.adicionarSerie(serie);
    }

    @Override
    public void desfazer(){
        if(this.serieCriada != null){
            serieService.removerSerie(serieCriada.getIdSerie());
        }
    }
}
