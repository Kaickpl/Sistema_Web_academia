package br.com.upe.academia.AcademiaWeb.Services;

import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ComentarioDTO;
import br.com.upe.academia.AcademiaWeb.Entities.DTOs.ExercicioSessaoDTO;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.utils.SerieSessaoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ExercicioSessaoService {
    public ExercicioSessao buscarExercicioSessao(UUID idTreinoSessao, UUID idExercicioSessao);

    public List<SerieSessao> listarSeriesExecucao(UUID idExercicio, UUID idTreinoSessao);

    public ExercicioSessao reinserirSeries(List<SerieSessao> serieSessao, UUID idSessao , UUID idExercicio);

    public ExercicioSessao salvarExercicioSessao(ExercicioSessaoDTO exerciciosessaoDTO);

    public void deletarExercicioSessao(UUID idExercicio ,UUID idSessao);

    public ExercicioSessao adicionarComentario(UUID idExercicioSessao, UUID idSessao , String comentario);
}
