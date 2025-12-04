package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TreinoExercicioResponseDTO {
    private String nomeDoExercicio;
    private String descricaoExercicio;
    private String tempoDeDescanso;
    private UUID idTreinoExercicio;

    private List<SerieDTO> series = new ArrayList<>();
}
