package br.com.upe.academia.AcademiaWeb.utils;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
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
        serie.setNumeroDeRepeticoes(dto.getNumeroDeRepeticoes());
        serie.setPesoDaSerie(dto.getPesoDaSerie());
        serie.setConcluida(dto.isConcluida());

        UUID idExercicio = dto.getIdExercicio();
        if (idExercicio != null) {
            Exercicio exercicio = new Exercicio();
            exercicio.setIdExercicio(idExercicio);
            serie.setExercicio(exercicio);
        }

        return serie;
    }

    public SerieDTO toResponseDTO(Serie serie) {
        if (serie == null) {
            return null;
        }

        return new SerieDTO(serie);
    }
}
