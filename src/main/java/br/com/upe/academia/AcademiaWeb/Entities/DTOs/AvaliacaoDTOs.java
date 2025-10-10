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

public class AvaliacaoDTOs {
    private UUID alunoId;
    private UUID personalId;
    private UUID avaliacaoId;
    private UUID medidasId;
    private LocalDate dataSolicitacao;
    private LocalDate dataAvaliacao;
    private String objetivoAvaliacao;
    public AvaliacaoDTOs(Avaliacao Avaliacao) {
        this.alunoId = Avaliacao.getAluno().getIdUsuario();
        this.personalId = Avaliacao.getPersonal().getIdUsuario();
        this.medidasId = Avaliacao.getMedidasCorporais().getIdMedidas();
        this.avaliacaoId = Avaliacao.getIdAvaliacao();
        this.dataSolicitacao = Avaliacao.getDataSolicitacao();
        this.dataAvaliacao = Avaliacao.getDataAvaliacao();
        this.objetivoAvaliacao = Avaliacao.getObjetivoAvaliacao();

    }
}
