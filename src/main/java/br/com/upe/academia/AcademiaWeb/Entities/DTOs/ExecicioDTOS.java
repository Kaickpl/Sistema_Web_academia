package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
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

public class ExecicioDTOS {
    private UUID idExercicio;
    private String nomeExercicio;
    private String descricaoExercicio;
    private Duration tempoDeDescanso;
    public ExecicioDTOS(Exercicio exercicio) {
        this.idExercicio = exercicio.getIdExercicio();
        this.nomeExercicio = exercicio.getNomeExercicio();
        this.descricaoExercicio = exercicio.getDescricaoExercicio();
        this.tempoDeDescanso = exercicio.getTempoDeDescanso();
    }

}
