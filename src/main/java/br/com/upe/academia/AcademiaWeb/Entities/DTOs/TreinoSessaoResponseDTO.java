package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.utils.DurationManager;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TreinoSessaoResponseDTO {
    private UUID idTreinoSessao;
    private boolean confirmarFechamento;
    private Instant dataFinal;
    private String duration;
    private String comentario;

    private String duracaoFinal(Duration duration){
        return DurationManager.formatDuration(duration);
    }
}
