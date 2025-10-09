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

public class SerieDTOs {
    private UUID idSerie;
    private Integer numeroDeRepeticoes;
    private Float pesoDaSerie;
    private boolean isConcluida = false;
    public SerieDTOs(Serie serie) {
        this.idSerie = serie.getIdSerie();
        this.numeroDeRepeticoes = serie.getNumeroDeRepeticoes();
        this.pesoDaSerie = serie.getPesoDaSerie();
        this.isConcluida = serie.isConcluida();
    }
}
