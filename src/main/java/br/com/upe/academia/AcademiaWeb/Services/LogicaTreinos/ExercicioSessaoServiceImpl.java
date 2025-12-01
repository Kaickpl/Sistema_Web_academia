package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ComentarioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Exceptions.InformacaoNaoEncontradoException;
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

    @Override
    public ExercicioSessao buscarExercicioSessao(UUID id) {
        return exercicioSessaoRepository.findById(id).orElseThrow( () -> new InformacaoNaoEncontradoException("Execução de Exercicio não encontrada"));
    }

    @Override
    public List<SerieSessao> listarSeriesExecucao(UUID idExercicio) {
        ExercicioSessao exercicioSessao = buscarExercicioSessao(idExercicio);
        return new ArrayList<>(exercicioSessao.getSeriesRealizadas());
    }

    @Override
    public ExercicioSessao reinserirSeries(List<SerieSessao> serieSessao, UUID idExercicio) {
        ExercicioSessao exercicioSessao = buscarExercicioSessao(idExercicio);
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
        ExercicioSessao exercicioSessao = exercicioSessaoMapper.toEntity(exerciciosessaoDTO);

        return exercicioSessaoRepository.save(exercicioSessao);
    }

    @Override
    @Transactional
    public void deletarExercicioSessao(UUID idExercicio) {
        this.buscarExercicioSessao(idExercicio);
        exercicioSessaoRepository.deleteById(idExercicio);
    }

    @Override
    public ExercicioSessao adicionarComentario(UUID idExercicioSessao, String comentario) {
        ExercicioSessao exercicioSessao = buscarExercicioSessao(idExercicioSessao);
        exercicioSessao.setComentario(comentario);
        return exercicioSessaoRepository.save(exercicioSessao);
    }

}
