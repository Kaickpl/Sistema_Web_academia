package br.com.upe.academia.AcademiaWeb.Entities.DTOs;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TreinoSessaoDTO {
    private UUID idTreinoSessao;
    private UUID idAluno;
    @NonNull
    private UUID idTreinoTemplate;
}
