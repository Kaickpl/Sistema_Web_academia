package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.utils.DurationDeserializer;
import br.com.upe.academia.AcademiaWeb.utils.DurationSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Duration;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ExercicioDTO {
    private UUID idExercicio;
    private String nomeExercicio;
    private String descricaoExercicio;
    @JsonDeserialize(using = DurationDeserializer.class)
    @JsonSerialize(using = DurationSerializer.class)
    private Duration tempoDeDescanso;
    private UUID idTreino;

    public ExercicioDTO(Exercicio exercicio) {
        this.idExercicio = exercicio.getIdExercicio();
        this.nomeExercicio = exercicio.getNomeExercicio();
        this.descricaoExercicio = exercicio.getDescricaoExercicio();
        this.tempoDeDescanso = exercicio.getTempoDeDescanso();

        if (exercicio.getTreino() != null) {
            this.idTreino = exercicio.getTreino().getIdTreino();
        }


    }

}
