package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SerieSessaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.SessaoProgressaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;

import java.util.List;
import java.util.UUID;

public interface SerieSessaoService {
    public SerieSessao buscarSerieSessao(UUID idSerieSessao);

    public SerieSessao salvarSerieSessao(SerieSessaoDTO serieSessaoDTO);

    public void removerSerieSessao(UUID idSerieSessao);

    public double calcularVolumeTotal(UUID idSerieSessao);

    public SerieSessao editarSerieSessao(SerieSessaoDTO serieSessaodto);

    public SerieSessao salvarEntidade(SerieSessao serieSessao);

    public SerieSessaoResponseDTO buscarRecordPorExercicio(UUID idExercicioTemplate, UUID idAluno);
}
