package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Services.SerieSessaoService;
import br.com.upe.academia.AcademiaWeb.utils.SerieSessaoMapper;

import java.util.UUID;

public class ExecutavelDeletarSerieSessao implements Executavel {
    private SerieSessao serieSessao;
    private SerieSessaoService serieSessaoService;
    private SerieSessaoMapper serieSessaoMapper;
    private UUID serieSessaoId;

    public ExecutavelDeletarSerieSessao(SerieSessaoService service, SerieSessaoMapper serieSessaoMapper, SerieSessaoDTO serieSessaoDTO) {
        this.serieSessaoService = service;
        this.serieSessaoMapper =  serieSessaoMapper;
        this.serieSessaoId = serieSessaoDTO.getIdSerieSessao();
    }

    @Override
    public void executar() {
        this.serieSessao = serieSessaoService.buscarSerieSessao(serieSessaoId);
        serieSessaoService.removerSerieSessao(this.serieSessaoId);
    }

    @Override
    public void desfazer() {
        if(this.serieSessao != null) {
            this.serieSessao.setIdSerieSessao(null);
            SerieSessaoDTO dtoParaReinserir = serieSessaoMapper.toDTO(this.serieSessao);
            this.serieSessao = serieSessaoService.salvarSerieSessao(dtoParaReinserir);
        }
    }


}

