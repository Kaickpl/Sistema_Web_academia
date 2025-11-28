package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SerieDTO {
    private UUID idSerie;
    private UUID idTreinoExercicio;

    public SerieDTO(UUID idSerie) {
    }
}

