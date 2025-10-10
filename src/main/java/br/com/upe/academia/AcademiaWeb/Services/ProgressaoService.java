package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ProgressaoDTOs;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Progressao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProgressaoService {
    Progressao salvaCarga(ProgressaoDTOs progressaoDTOs);

    List<Progressao> getHistoricoCarga(UUID alunoId, String nomeExercicico);
}
