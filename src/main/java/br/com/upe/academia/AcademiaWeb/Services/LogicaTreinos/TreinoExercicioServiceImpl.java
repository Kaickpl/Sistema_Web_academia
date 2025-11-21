package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TempoDeDescansoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TreinoExercicioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import br.com.upe.academia.AcademiaWeb.Repositories.TreinoExercicioRepository;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoExercicioService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import br.com.upe.academia.AcademiaWeb.utils.TreinoExercicioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TreinoExercicioServiceImpl implements TreinoExercicioService {

    @Autowired
    private TreinoExercicioRepository treinoExercicioRepository;

    @Autowired
    private TreinoExercicioMapper treinoExercicioMapper;

    @Autowired
    private TreinoService treinoService;

    @Override
    public TreinoExercicio buscarTreinoExercicio(UUID idTreinoExercicio) {
        return treinoExercicioRepository.findById(idTreinoExercicio).orElseThrow(() -> new RuntimeException("Regra de exercicio não encontrada"));
    }

    @Override
    public TempoDeDescansoResponseDTO buscarTempoDeDescansoBase(UUID idTreinoExercicio) {
        TreinoExercicio treinoExercicio = buscarTreinoExercicio(idTreinoExercicio);
        return this.treinoExercicioMapper.toTempoDescansoDTO(treinoExercicio);
    }

    @Override
    public void deletarRegraDeExercicio(UUID idTreinoExercicio) {
        this.treinoExercicioRepository.deleteById(idTreinoExercicio);
    }

    @Override
    public TreinoExercicio salvarRegra(TreinoExercicio regra){
        return treinoExercicioRepository.save(regra);
    }

}
