package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.utils.DurationManager;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TreinoSessaoResponseDTO {
    private String nomeAluno;
    private UUID idTreinoSessao;
    private boolean confirmarFechamento;
    private Instant dataFinal;
    private String duration;
    private String comentario;

    private List<ExercicioSessaoResponseDTO> exercicios = new ArrayList<>();

    public double getVolumeTotal() {
        double volumeTotal = 0;
        for (ExercicioSessaoResponseDTO exercicio : exercicios) {
            volumeTotal += exercicio.getVolumeTotal();
        }
        return volumeTotal;

    }
}
