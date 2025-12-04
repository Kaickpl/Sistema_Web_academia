package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ComentarioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoSessao;
import br.com.upe.academia.AcademiaWeb.Exceptions.InformacaoNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Exceptions.ValorInvalidoException;
import br.com.upe.academia.AcademiaWeb.Repositories.ExercicioSessaoRepository;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioSessaoService;
import br.com.upe.academia.AcademiaWeb.Services.SerieSessaoService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoSessaoService;
import br.com.upe.academia.AcademiaWeb.utils.ExercicioSessaoMapper;
import br.com.upe.academia.AcademiaWeb.utils.SerieSessaoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExercicioSessaoServiceImpl implements ExercicioSessaoService {

    @Autowired
    private ExercicioSessaoRepository exercicioSessaoRepository;
    @Autowired
    private ExercicioService exercicioService;
    @Autowired
    private ExercicioSessaoMapper exercicioSessaoMapper;
    @Autowired
    private SerieSessaoService serieSessaoService;
    @Autowired
    private TreinoSessaoService treinoSessaoService;

    @Override
    public ExercicioSessao buscarExercicioSessao(UUID idTreinoSessao, UUID idExercicioSessao) {
        ExercicioSessao exSessao = exercicioSessaoRepository.findById(idExercicioSessao).orElseThrow( () -> new InformacaoNaoEncontradoException("Execução de Exercicio não encontrada com ID " + idExercicioSessao));

        if(!exSessao.getTreinoExecucao().getIdTreinoSessao().equals(idTreinoSessao)){
            throw new ValorInvalidoException("A execução de exercicio a ser encontrada não pertence à sessão de treino informada na URL.");
        }

        return exSessao;
    }

    @Override
    public List<SerieSessao> listarSeriesExecucao(UUID idTreinoSessao , UUID idExercicio) {
        ExercicioSessao exercicioSessao = buscarExercicioSessao(idTreinoSessao, idExercicio);
        return new ArrayList<>(exercicioSessao.getSeriesRealizadas());
    }

    @Override
    public ExercicioSessao reinserirSeries(List<SerieSessao> serieSessao, UUID idSessao , UUID idExercicio) {
        ExercicioSessao exercicioSessao = buscarExercicioSessao(idSessao, idExercicio);
        for(SerieSessao series : serieSessao){
            series.setIdSerieSessao(null);
            series.setExercicioSessao(exercicioSessao);
            serieSessaoService.salvarEntidade(series);
        }
        return exercicioSessao;
    }

    @Override
    @Transactional
    public ExercicioSessao salvarExercicioSessao(ExercicioSessaoDTO exerciciosessaoDTO) {
        exercicioService.buscarExercicio(exerciciosessaoDTO.getIdExercicio());
        treinoSessaoService.buscarSessaoPorIdUnico(exerciciosessaoDTO.getIdTreinoSessao());
        ExercicioSessao exercicioSessao = exercicioSessaoRepository.save(exercicioSessaoMapper.toEntity(exerciciosessaoDTO));

        return exercicioSessao;
    }

    @Override
    @Transactional
    public void deletarExercicioSessao(UUID idSessao ,UUID idExercicio) {
        this.buscarExercicioSessao(idSessao, idExercicio);
        exercicioSessaoRepository.deleteById(idExercicio);
    }

    @Override
    public ExercicioSessao adicionarComentario(UUID idSessao, UUID idExercicioSessao , String comentario) {
        ExercicioSessao exercicioSessao = buscarExercicioSessao(idSessao,idExercicioSessao);
        exercicioSessao.setComentario(comentario);
        return exercicioSessaoRepository.save(exercicioSessao);
    }

}
