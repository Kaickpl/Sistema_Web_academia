package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import br.com.upe.academia.AcademiaWeb.Services.SerieService;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ExecutavelCriarSerie implements Executavel {
    private SerieService serieService;
    private UUID idTreinoExercicio;
    private UUID idSerieCriada;
    private Serie serieCriada;

    public ExecutavelCriarSerie(SerieService serieService, UUID idTreinoExercicio) {
        this.serieService = serieService;
        this.idTreinoExercicio = idTreinoExercicio;
    }

    @Override
    public void executar() {
        Serie serie = new Serie();
        TreinoExercicio treinoExReff = new TreinoExercicio();
        treinoExReff.setIdTreinoExercicio(idTreinoExercicio);
        serie.setTreinoExercicio(treinoExReff);
        this.serieCriada = serieService.adicionarSerie(serie);
        this.idSerieCriada = serie.getIdSerie();
    }

    @Override
    public void desfazer(){
        if(this.serieCriada != null){
            serieService.removerSerie(serieCriada.getIdSerie());
        }
    }
}
