package br.com.upe.academia.AcademiaWeb.utils;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class ExercicioMapper {

    public Exercicio toEntity(ExercicioDTO dto){
        if(dto == null) return null;

        Exercicio exercicio = new Exercicio();

        exercicio.setIdExercicio(dto.getIdExercicio());
        exercicio.setNomeExercicio(dto.getNomeExercicio());
        exercicio.setDescricaoExercicio(dto.getDescricaoExercicio());
        exercicio.setTempoDeDescanso(dto.getTempoDeDescansoBase());

        return exercicio;
    }

    public ExercicioDTO toDTO(Exercicio exercicio){
        if(exercicio == null) return null;

        return new ExercicioDTO(exercicio);
    }

}
