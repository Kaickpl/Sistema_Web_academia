package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.ExercicioSessao;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.SerieSessao;
import br.com.upe.academia.AcademiaWeb.Repositories.ExercicioSessaoRepository;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioSessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

//ainda a implementar

@Service
public class ExercicioSessaoServiceImpl implements ExercicioSessaoService {

    @Autowired
    ExercicioSessaoRepository exercicioSessaoRepository;

    @Override
    public ExercicioSessao buscarExercicioSessao(UUID id) {
        return exercicioSessaoRepository.findById(id).orElse(null);
    }

    @Override
    public List<SerieSessao> listarSeriesExecucao(UUID idExercicio) {
        return List.of();
    }

    @Override
    public ExercicioSessao salvarExercicioSessao(ExercicioSessao exerciciosessao) {
        return null;
    }

    @Override
    public void deletarExercicioSessao(UUID idExercicio) {

    }
}
