package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import br.com.upe.academia.AcademiaWeb.Entities.Enums.MusculoTrabalhado;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;


import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExercicioDTO {
    private UUID idExercicio;
    private String nomeExercicio;
    private String descricaoExercicio;
    @NonNull
    private MusculoTrabalhado musculoPrincipal;
}
