package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TreinoSessaoResponseGetDTO {
    private UUID idTreinoSessao;
    private String dataExecucao;
    private String NomeAluno;
    private String treinoTemplate;
    private boolean statusFechamento;
    private String duration;
}
