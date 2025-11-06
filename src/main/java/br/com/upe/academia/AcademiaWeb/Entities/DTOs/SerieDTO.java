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
    private UUID idExercicio;

    public SerieDTO(Serie serie) {
        this.idSerie = serie.getIdSerie();

        if (serie.getExercicio() != null) {
            this.idExercicio = serie.getExercicio().getIdExercicio();
        }
    }
}
