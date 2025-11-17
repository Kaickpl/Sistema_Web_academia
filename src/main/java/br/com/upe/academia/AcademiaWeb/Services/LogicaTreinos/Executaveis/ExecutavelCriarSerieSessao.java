package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Services.SerieSessaoService;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ExecutavelCriarSerieSessao implements Executavel{
    private SerieSessao serieSessaoCriada;
    private SerieSessaoService serieSessaoService;
    private SerieSessaoDTO serieSessaoDTO;
    private UUID idSerieSessao;

    public ExecutavelCriarSerieSessao(SerieSessaoService serieSessaoService, SerieSessaoDTO serieSessaoDTO) {
        this.serieSessaoService = serieSessaoService;
        this.serieSessaoDTO = serieSessaoDTO;
    }

    @Override
    public void executar() {
        this.serieSessaoCriada = serieSessaoService.salvarSerieSessao(serieSessaoDTO);
        this.idSerieSessao = serieSessaoCriada.getIdSerieSessao();
    }

    @Override
    public void desfazer() {
        if(idSerieSessao != null){
            serieSessaoService.removerSerieSessao(idSerieSessao);
        }
    }
}
