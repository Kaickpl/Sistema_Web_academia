package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.Avaliacao;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AvaliacaoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.AvaliacaoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ModificarDataAvaliacaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ModificarPersonalAvaliacaoDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public interface AvaliacaoService {
    Avaliacao criarAvaliacao(AvaliacaoDTOs avaliacaoDTOs);
    List<AvaliacaoResponseDTO> mostrarAvaliacaoAluno(UUID alunoId);
    List<AvaliacaoResponseDTO> mostrarAvaliacaoPersonal(String cref);

    List<AvaliacaoResponseDTO> mostrarAvaliacaoPersonalEData(String cref, LocalDate data);

    boolean removerAvaliacao(UUID idAvaliacao);

    Avaliacao buscarPorId(UUID idAvaliacao);

    AvaliacaoResponseDTO mostrarProximaAvaliacaoAluno(UUID alunoId);

    Avaliacao alterarDataAvaliacao(UUID idAvaliacao, ModificarDataAvaliacaoDTO modificarDataAvaliacaoDTO);

    Avaliacao alterarPersonal(UUID idAvaliacao, ModificarPersonalAvaliacaoDTO avaliacaoDTOs);
}
