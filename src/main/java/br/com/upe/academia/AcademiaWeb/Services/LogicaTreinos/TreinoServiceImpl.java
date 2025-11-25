package br.com.upe.academia.AcademiaWeb.Services.LogicaTreinos;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Serie;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.Treino;
import br.com.upe.academia.AcademiaWeb.Entities.LogicaTreinos.TreinoExercicio;
import br.com.upe.academia.AcademiaWeb.Repositories.TreinoRepository;
import br.com.upe.academia.AcademiaWeb.Services.TreinoExercicioService;
import br.com.upe.academia.AcademiaWeb.Services.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TreinoServiceImpl implements TreinoService {

    @Autowired
    TreinoRepository treinoRepository;
    @Autowired
    private TreinoExercicioService treinoExercicioService;

    public Treino buscarTreino(UUID idTreino){
        Optional<Treino> treino = treinoRepository.findById(idTreino);
        return treino.orElseThrow(() -> new RuntimeException("Treino não encontrado"));
    }

    @Override
    @Transactional
    public Treino criarTreino(Treino treino){
        return this.treinoRepository.save(treino);
    }

    @Override
    @Transactional
    public Treino atualizarTreino(Treino treino){
        Treino treinoAtualizado = buscarTreino(treino.getIdTreino());
        treinoAtualizado.setNome(treino.getNome());
        return this.treinoRepository.save(treinoAtualizado);
    }

    @Override
    @Transactional
    public void deletarTreino(UUID idTreino) {
        Treino treino = this.buscarTreino(idTreino);
        treinoRepository.desvincularTreinoDeTodosAlunos(idTreino);
        this.treinoRepository.deleteById(idTreino);
    }

    @Override
    public Treino deepCopyTreino(Treino treinoOriginal){
        Treino novoTreino = new Treino();
        novoTreino.setNome(treinoOriginal.getNome() + " - Cópia");

        List<TreinoExercicio> novasRegras = new ArrayList<>();

        for(TreinoExercicio regraOriginal : treinoOriginal.getRegrasDeExercicios()){
            TreinoExercicio novaRegra = deepCopyRegraExercicio(regraOriginal, novoTreino);
            novasRegras.add(novaRegra);
        }

        novoTreino.setRegrasDeExercicios(novasRegras);

        return treinoRepository.save(novoTreino);
        }

    private TreinoExercicio deepCopyRegraExercicio(TreinoExercicio exercicioOriginal,Treino novoPai){
        TreinoExercicio novaRegra = new TreinoExercicio();
        novaRegra.setTempoDeDescanso(exercicioOriginal.getTempoDeDescanso());

        novaRegra.setExercicioTemplate(exercicioOriginal.getExercicioTemplate());

        novaRegra.setTreino(novoPai);

        List<Serie> novasSeries =  new ArrayList<>();

        for(Serie serieOriginal : exercicioOriginal.getSeriesTemplate()){
            Serie novaSerie = new Serie();
            novaSerie.setTreinoExercicio(novaRegra);
            novasSeries.add(novaSerie);
        }

        novaRegra.setSeriesTemplate(novasSeries);

        return novaRegra;
    }

    public void restaurarRegras(Treino novoTreino, List<TreinoExercicio> regrasAntigas){

        for(TreinoExercicio regra : regrasAntigas){
            regra.setIdTreinoExercicio(null);
            regra.setTreino(novoTreino);
            if(regra.getSeriesTemplate() != null){
                List<Serie> seriesParaSalvar = new ArrayList<>(regra.getSeriesTemplate());
                regra.setSeriesTemplate(new ArrayList<>());

                for(Serie serie : seriesParaSalvar){
                    serie.setIdSerie(null);
                    serie.setTreinoExercicio(regra);
                    regra.getSeriesTemplate().add(serie);
                }
            }

            treinoExercicioService.salvarRegra(regra);
        }
    }
}

