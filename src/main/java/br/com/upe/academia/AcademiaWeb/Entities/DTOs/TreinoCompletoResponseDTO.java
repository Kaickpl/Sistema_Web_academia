package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TreinoCompletoResponseDTO {
    private String nomeTreino;
    private UUID idTreino;
    private List<TreinoExercicioResponseDTO> treinoExercicioInfos;
}
