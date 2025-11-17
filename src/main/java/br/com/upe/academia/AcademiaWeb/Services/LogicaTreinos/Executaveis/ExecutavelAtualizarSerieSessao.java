package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos.Executaveis;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Services.SerieSessaoService;
import br.com.upe.academia.AcademiaWeb.utils.SerieSessaoMapper;

public class ExecutavelAtualizarSerieSessao implements Executavel {
    private SerieSessaoService serieSessaoService;
    private SerieSessaoMapper serieSessaoMapper;
    private SerieSessaoDTO dadosNovosDTO;

    private SerieSessao serieAntiga;

    private SerieSessao serieAtualizada;

    public ExecutavelAtualizarSerieSessao(SerieSessaoService SerieSessaoService, SerieSessaoMapper serieSessaoMapper, SerieSessaoDTO dadosNovosDTO) {
        this.serieSessaoService = SerieSessaoService;
        this.serieSessaoMapper = serieSessaoMapper;
        this.dadosNovosDTO = dadosNovosDTO;
    }

    @Override
    public void executar() {
        this.serieAntiga = serieSessaoService.buscarSerieSessao(dadosNovosDTO.getIdSerieSessao());
        this.serieAtualizada = serieSessaoService.editarSerieSessao(dadosNovosDTO);
    }

    @Override
    public void desfazer() {
        if(this.serieAntiga != null && this.serieAtualizada != null) {
            SerieSessaoDTO dtoAntigo = serieSessaoMapper.toDTO(this.serieAntiga);
            this.serieAtualizada = serieSessaoService.editarSerieSessao(dtoAntigo);
        }
    }
}
