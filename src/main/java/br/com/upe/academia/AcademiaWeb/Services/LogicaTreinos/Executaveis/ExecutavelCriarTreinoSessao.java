package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoSessao;
import br.com.upe.academia.AcademiaWeb.Services.TreinoSessaoService;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ExecutavelCriarTreinoSessao implements Executavel{
    private TreinoSessaoDTO treinoSessaoDTO;
    private TreinoSessaoService treinoSessaoService;
    private TreinoSessao sessaoCriada;
    private UUID treinoSessaoCriadaId;

    public ExecutavelCriarTreinoSessao(TreinoSessaoService service, TreinoSessaoDTO treinoSessaoDTO) {
        this.treinoSessaoService = service;
        this.treinoSessaoDTO = treinoSessaoDTO;
    }

    @Override
    public void executar() {
        this.sessaoCriada = treinoSessaoService.iniciarTreinoSessao(treinoSessaoDTO);
        this.treinoSessaoCriadaId = sessaoCriada.getIdTreinoSessao();
    }

    @Override
    public void desfazer() {
        if(this.treinoSessaoCriadaId != null)
            treinoSessaoService.apagarTreinoSessao(this.treinoSessaoCriadaId);
    }

}

