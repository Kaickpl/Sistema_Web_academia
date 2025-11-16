package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.utils.DurationManager;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TreinoSessaoResponseDTO {
    @NotNull
    private UUID idTreinoSessao;
    private boolean confirmarFechamento;
    private Instant dataFinal;
    private String duration;

    private String duracaoFinal(Duration duration){
        return DurationManager.toStringTime(duration);
    }
}
