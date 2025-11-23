package br.com.upe.academia.AcademiaWeb.Services;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TempoDeDescansoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TempoDeDescansoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoExercicioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface TreinoExercicioService {
    public TreinoExercicio buscarTreinoExercicio(UUID idTreinoExercicio);

    public TempoDeDescansoResponseDTO buscarTempoDeDescansoBase(UUID idTreinoExercicio);

    public void deletarRegraDeExercicio(UUID idTreinoExercicio);

    public TreinoExercicio salvarRegra(TreinoExercicio regra);

    public TreinoExercicio atualizarTempoDeDescanso(UUID idRegra, TempoDeDescansoDTO tempoDeDescanso);

}