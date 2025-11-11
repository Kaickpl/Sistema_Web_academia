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
public class TreinoSessaoDTO {
    private UUID idTreinoSessao;
    private Instant dataTreinoSessao = Instant.now();
    private Instant tempoFinalizacao;
    private boolean concluido = false;
    private UUID idAluno;
    private UUID idTreinoTemplate;
}
