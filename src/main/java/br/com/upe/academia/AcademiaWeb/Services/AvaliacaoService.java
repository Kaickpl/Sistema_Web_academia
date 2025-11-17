package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Avaliacao;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AvaliacaoDTOs;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public interface AvaliacaoService {
    public Avaliacao criarAvaliacao(AvaliacaoDTOs avaliacaoDTOs);
    public List<Avaliacao> mostrarAvaliacaoAluno(UUID alunoId);
    public List<Avaliacao> mostrarAvaliacaoPersonal(String cref);

    List<Avaliacao> mostrarAvaliacaoPersonalEData(String cref, LocalDate data);

    boolean removerAvaliacao(UUID idAvaliacao);

    Avaliacao buscarPorId(UUID idAvaliacao);

    Avaliacao alterarDataAvaliacao(UUID idAvaliacao, AvaliacaoDTOs avaliacaoDTOs);
}
