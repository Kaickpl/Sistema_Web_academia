package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TempoDeDescansoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.TempoDeDescansoResponseDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import br.com.upe.academia.AcademiaWeb.Exceptions.InformacaoNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Repositories.ExercicioRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.TreinoExercicioRepository;
import br.com.upe.academia.AcademiaWeb.Repositories.TreinoRepository;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoExercicioService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import br.com.upe.academia.AcademiaWeb.utils.DurationManager;
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
    private TreinoRepository treinoRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Override
    public TreinoExercicio buscarTreinoExercicio(UUID idTreinoExercicio) {
        return treinoExercicioRepository.findById(idTreinoExercicio).orElseThrow(() -> new InformacaoNaoEncontradoException("Regra de Treino-Exercicio não encontrada com o ID " +  idTreinoExercicio));
    }

    @Override
    public TempoDeDescansoResponseDTO buscarTempoDeDescansoBase(UUID idTreinoExercicio) {
        TreinoExercicio treinoExercicio = buscarTreinoExercicio(idTreinoExercicio);
        return this.treinoExercicioMapper.toTempoDescansoDTO(treinoExercicio);
    }

    @Override
    public void deletarRegraDeExercicio(UUID idTreinoExercicio) {
        buscarTreinoExercicio(idTreinoExercicio);
        this.treinoExercicioRepository.deleteById(idTreinoExercicio);
    }

    @Override
    public TreinoExercicio salvarRegra(TreinoExercicio regra){
        exercicioRepository.findById(regra.getExercicioTemplate().getIdExercicio()).orElseThrow(() -> new InformacaoNaoEncontradoException("Exercicio não encontrado com o ID " + regra.getExercicioTemplate().getIdExercicio()));
        treinoRepository.findById(regra.getTreino().getIdTreino()).orElseThrow(() -> new InformacaoNaoEncontradoException("Treino não encontrado com o ID " +  regra.getTreino().getIdTreino()));

        return treinoExercicioRepository.save(regra);
    }

    @Override
    public TreinoExercicio atualizarTempoDeDescanso(UUID idRegra, TempoDeDescansoDTO tempoDeDescanso) {

        TreinoExercicio regra = this.buscarTreinoExercicio(idRegra);
        if(tempoDeDescanso.getTempoDeDescanso() != null){
            regra.setTempoDeDescanso(DurationManager.parseDuration(tempoDeDescanso.getTempoDeDescanso()));
        }
        return  treinoExercicioRepository.save(regra);
    }

}
