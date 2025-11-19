package br.com.upe.academia.AcademiaWeb.utils;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import org.springframework.stereotype.Component;
import static br.com.upe.academia.AcademiaWeb.utils.DurationManager.formatDuration;

@Component
public class ExercicioMapper {


    public Exercicio toEntity(ExercicioDTO dto){
        if(dto == null) return null;

        Exercicio exercicio = new Exercicio();

        exercicio.setIdExercicio(dto.getIdExercicio());
        exercicio.setNomeExercicio(dto.getNomeExercicio());
        exercicio.setDescricaoExercicio(dto.getDescricaoExercicio());

        exercicio.setTempoDeDescanso(DurationManager.parseDuration(dto.getTempoDescandoBase()));

        return exercicio;
    }

    public ExercicioDTO toDTO(Exercicio exercicio){
        if(exercicio == null) return null;

        ExercicioDTO dto = new ExercicioDTO();
        dto.setIdExercicio(exercicio.getIdExercicio());
        dto.setNomeExercicio(exercicio.getNomeExercicio());
        dto.setDescricaoExercicio(exercicio.getDescricaoExercicio());

        dto.setTempoDescandoBase(formatDuration(exercicio.getTempoDeDescanso()));

        return dto;
    }
}