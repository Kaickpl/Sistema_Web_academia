package br.com.upe.academia.AcademiaWeb.utils;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ExercicioMapper {

    private DurationManager durationManager;

    public Exercicio toEntity(ExercicioDTO dto){
        if(dto == null) return null;

        Exercicio exercicio = new Exercicio();

        exercicio.setIdExercicio(dto.getIdExercicio());
        exercicio.setNomeExercicio(dto.getNomeExercicio());
        exercicio.setDescricaoExercicio(dto.getDescricaoExercicio());
        exercicio.setTempoDeDescanso(durationManager.toDuration(dto.getTempoDescansoBase()));

        return exercicio;
    }

    public ExercicioDTO toDTO(Exercicio exercicio){
        if(exercicio == null) return null;

        ExercicioDTO dto = new ExercicioDTO();
        // Mapeamento dos campos (exemplo, você completaria o resto)
        dto.setIdExercicio(exercicio.getIdExercicio());
        dto.setNomeExercicio(exercicio.getNomeExercicio());
        dto.setDescricaoExercicio(exercicio.getDescricaoExercicio());

        // CONVERSÃO DE SAÍDA: Duration -> String formatada
        dto.setTempoDescansoBase(durationManager.toStringTime(exercicio.getTempoDeDescanso()));

        return dto;
    }

}
