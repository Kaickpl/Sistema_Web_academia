package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Exercicio;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import br.com.upe.academia.AcademiaWeb.Exceptions.InformacaoNaoEncontradoException;
import br.com.upe.academia.AcademiaWeb.Repositories.ExercicioRepository;
import br.com.upe.academia.AcademiaWeb.Services.ExercicioService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoExercicioService;
import br.com.upe.academia.AcademiaWeb.utils.TreinoExercicioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExercicioServiceImpl implements ExercicioService {

    @Autowired
    ExercicioRepository exercicioRepository;

    @Autowired
    private TreinoExercicioService treinoExercicioService;

    @Override
    public Exercicio buscarExercicio(UUID idExercicio) {
        Optional<Exercicio> exercicio = exercicioRepository.findById(idExercicio);
        return exercicio.orElseThrow(() -> new InformacaoNaoEncontradoException("Exercicio não encontrado com ID " + idExercicio));
    }

    @Override
    public List<Exercicio> buscarExercicioPorTreino(UUID idTreino) {
     return exercicioRepository.findByTreinoIdTreino(idTreino);
    }

    @Override
    public List<Exercicio> buscarTodosOsExercicios() {
        return exercicioRepository.findAll();
    }

    @Transactional
    @Override
    public Exercicio adicionarExercicio(Exercicio exercicio) {
        return this.exercicioRepository.save(exercicio);
    }

    @Transactional
    @Override
    public Exercicio alterarExercicio(Exercicio exercicio) {
        Exercicio exercicioAtt = buscarExercicio(exercicio.getIdExercicio());
        exercicioAtt.setNomeExercicio(exercicio.getNomeExercicio());
        exercicioAtt.setDescricaoExercicio(exercicio.getDescricaoExercicio());
        exercicioAtt.setMusculoPrincipal(exercicio.getMusculoPrincipal());
        return this.exercicioRepository.save(exercicioAtt);
    }

    @Override
    public void removerExercicio(UUID idExercicio) {
        exercicioRepository.deleteById(idExercicio);
    }

    @Override
    @Transactional
    public void restaurarLigacoesRegras(Exercicio novoExercicio, List<TreinoExercicio> regrasAntigas) {
        for(TreinoExercicio regra : regrasAntigas){
            regra.setIdTreinoExercicio(null);
            regra.setExercicioTemplate(novoExercicio);

            if(regra.getSeriesTemplate() != null){
                List<Serie> seriesAntigas = new ArrayList<>(regra.getSeriesTemplate());
                regra.setSeriesTemplate(new ArrayList<>());

                for(Serie serieVelha : seriesAntigas){
                    Serie novaSerie = new Serie();
                    novaSerie.setTreinoExercicio(regra);
                    regra.getSeriesTemplate().add(novaSerie);
                }
            }
            treinoExercicioService.salvarRegra(regra);
        }

    }
}
