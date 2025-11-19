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
    public Objetivos registrarObjetivo(ObjetivoRegistroDTO objetivosDto);
    public List<ObjetivosResponseDTO> mostrarTodosObjetivos(UUID alunoId);
    public List<ObjetivosResponseDTO> mostrarObjetivosNaoConcluidos(UUID alunoId);
    public List<ObjetivosResponseDTO> mostrarObjetivosConcluidos(UUID alunoId);
    public Objetivos atualizaObjetivo(UUID id, ObjetivoRegistroDTO objetivosDto);
    public void deletarObjetivo(UUID id);
    public List<ObjetivosDTO> mostrarObjetivosNaoConcluidosPorTipoMedida(UUID alunoId, String tipo);

}
