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
    private String emailPersonal;
    private UUID avaliacaoId;
    private UUID medidasId;
    private LocalDate dataAvaliacao;
    private String objetivoAvaliacao;
    public AvaliacaoDTOs(Avaliacao avaliacao) {
        this.alunoId = avaliacao.getAluno().getIdUsuario();
        this.emailPersonal = avaliacao.getPersonal().getEmail();
        this.medidasId = avaliacao.getMedidasCorporais().getIdMedidas();
        this.avaliacaoId = avaliacao.getIdAvaliacao();
        this.dataAvaliacao = avaliacao.getDataAvaliacao();
        this.objetivoAvaliacao = avaliacao.getObjetivoAvaliacao();
    }
}
