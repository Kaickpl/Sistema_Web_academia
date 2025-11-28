package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ObjetivoRegistroDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ObjetivosDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ObjetivosResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.Objetivos;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface ObjetivosService {
    Objetivos registrarObjetivo(ObjetivoRegistroDTO objetivosDto);
    List<ObjetivosResponseDTO> mostrarTodosObjetivos(UUID alunoId);
    List<ObjetivosResponseDTO> mostrarObjetivosNaoConcluidos(UUID alunoId);
    List<ObjetivosResponseDTO> mostrarObjetivosConcluidos(UUID alunoId);
    Objetivos atualizaObjetivo(UUID id, ObjetivoRegistroDTO objetivosDto);
    void deletarObjetivo(UUID id);
    List<ObjetivosDTO> mostrarObjetivosNaoConcluidosPorTipoMedida(UUID alunoId, String tipo);
    Objetivos registrarObjetivoExercicio(ObjetivoRegistroDTO objetivoRegistroDTO);
}
