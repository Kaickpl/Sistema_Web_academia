package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SerieDTO {
    private UUID idSerie;
    private Integer numeroDeRepeticoes;
    private Float pesoDaSerie;
    private boolean isConcluida = false;
    private UUID idExercicio;
    private Float volumeSerie;

    public SerieDTO(Serie serie) {
        this.idSerie = serie.getIdSerie();
        this.numeroDeRepeticoes = serie.getNumeroDeRepeticoes();
        this.pesoDaSerie = serie.getPesoDaSerie();
        this.isConcluida = serie.isConcluida();

        if (serie.getExercicio() != null) {
            this.idExercicio = serie.getExercicio().getIdExercicio();
        }
        this.volumeSerie = serie.getVolumeSerie();
    }
}
