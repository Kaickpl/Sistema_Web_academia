package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Avaliacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AvaliacaoResponseDTO {
    private String alunoEmail;
    private String personalEmail;
    private UUID avaliacaoId;
    private LocalDate dataSolicitacao;
    private LocalDate dataAvaliacao;
    private String objetivoAvaliacao;
    public AvaliacaoResponseDTO(Avaliacao avaliacao) {
        this.alunoEmail = avaliacao.getAluno().getEmail();
        this.personalEmail = avaliacao.getPersonal().getEmail();
        this.avaliacaoId = avaliacao.getIdAvaliacao();
        this.dataSolicitacao = avaliacao.getDataSolicitacao();
        this.dataAvaliacao = avaliacao.getDataAvaliacao();
        this.objetivoAvaliacao = avaliacao.getObjetivoAvaliacao();
    }
}
