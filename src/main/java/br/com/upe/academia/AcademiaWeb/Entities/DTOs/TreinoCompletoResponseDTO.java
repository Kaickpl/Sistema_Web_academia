package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TreinoCompletoResponseDTO {
    private UUID idTreino;
    private String nomeTreino;
    private List<TreinoExercicioResponseDTO> treinoExercicioInfos;
}
