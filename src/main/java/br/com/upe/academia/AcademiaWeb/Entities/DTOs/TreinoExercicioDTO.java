package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import br.com.upe.academia.AcademiaWeb.utils.DurationCustomConverter;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreinoExercicioDTO {
    private UUID idTreinoExercicio;
    private UUID idExercicio;
    private UUID idTreino;
    private String nomeTreino;
    private String tempoDeDescanso;
}
