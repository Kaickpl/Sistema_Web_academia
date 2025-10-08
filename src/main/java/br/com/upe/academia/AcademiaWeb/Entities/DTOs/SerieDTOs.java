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

    public boolean isConcluida() {
        return isConcluida;
    }

    public void setConcluida(boolean concluida) {
        isConcluida = concluida;
    }

    public UUID getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(UUID idSerie) {
        this.idSerie = idSerie;
    }

    public Float getPesoDaSerie() {
        return pesoDaSerie;
    }

    public void setPesoDaSerie(Float pesoDaSerie) {
        this.pesoDaSerie = pesoDaSerie;
    }

    public Integer getNumeroDeRepeticoes() {
        return numeroDeRepeticoes;
    }

    public void setNumeroDeRepeticoes(Integer numeroDeRepeticoes) {
        this.numeroDeRepeticoes = numeroDeRepeticoes;
    }
}
