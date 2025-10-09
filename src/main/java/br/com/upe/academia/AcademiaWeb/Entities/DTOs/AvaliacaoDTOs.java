package br.com.upe.academia.AcademiaWeb.Entities.DTOs;

import br.com.upe.academia.AcademiaWeb.Entities.Avaliacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AvaliacaoDTOs {
    private UUID IdAvaliacao;
    private Date dataSolicitacao;
    private Date dataAvaliacao;
    private String objeitvoAvaliacao;
    public AvaliacaoDTOs(Avaliacao Avaliacao) {
        this.IdAvaliacao = Avaliacao.getIdAvaliacao();
        this.dataSolicitacao = Avaliacao.getDataSolicitacao();
        this.dataAvaliacao = Avaliacao.getDataAvaliacao();
        this.objeitvoAvaliacao = Avaliacao.getObjeitvoAvaliacao();

    }
}
