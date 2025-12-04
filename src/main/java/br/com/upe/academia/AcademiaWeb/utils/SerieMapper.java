package br.com.upe.academia.AcademiaWeb.utils;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SerieMapper {

    public Serie toEntity(SerieDTO dto) {
        if (dto == null) {
            return null;
        }

        Serie serie = new Serie();

        serie.setIdSerie(dto.getIdSerie());

        UUID idTreinoExercicio = dto.getIdTreinoExercicio();
        if (idTreinoExercicio != null) {
            TreinoExercicio treinoExercicio = new TreinoExercicio();
            treinoExercicio.setIdTreinoExercicio(idTreinoExercicio);
            serie.setTreinoExercicio(treinoExercicio);
        }

        return serie;
    }

    public SerieDTO toDTO(Serie serie) {
        if (serie == null) {
            return null;
        }

        SerieDTO serieDTO = new SerieDTO();
        serieDTO.setIdSerie(serie.getIdSerie());
        serieDTO.setIdTreinoExercicio(serie.getTreinoExercicio().getIdTreinoExercicio());

        return serieDTO;
    }
}
